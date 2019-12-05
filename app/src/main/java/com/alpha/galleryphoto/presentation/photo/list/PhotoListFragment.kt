package com.alpha.galleryphoto.presentation.photo.list

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alpha.galleryphoto.domain.model.data.Photo
import com.alpha.galleryphoto.presentation.common.AListAdapter
import com.alpha.galleryphoto.presentation.common.BaseListFragment
import com.alpha.galleryphoto.presentation.photo.add.PhotoAddActivity
import com.alpha.galleryphoto.utils.FilesUtil
import com.alpha.galleryphoto.utils.list.GridSpacingItemDecoration
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.fragment_default_list.*
import javax.inject.Inject


//указан стандартный фрагмент

class PhotoListFragment : BaseListFragment<Photo, AListAdapter.DefaultViewHolder<Photo>>(),
    PhotoListView {

    @InjectPresenter
    @Inject
    lateinit var presenter: PhotoListPresenter


    @ProvidePresenter
    fun providePresenter() = presenter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        fab.setOnClickListener {
            startActivityForResult(PhotoAddActivity.getIntent(activity!!), RESULT_ADD)
        }
    }

    override fun initAdapter(): AListAdapter<Photo, AListAdapter.DefaultViewHolder<Photo>> {
        val adapter = PhotoListAdapter()

        adapter.onDataClickListener = { photo, _ ->

            photo.let {

                if (ContextCompat.checkSelfPermission(
                        activity!!,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    startSelectPhoto(it)
                } else {
                    ActivityCompat.requestPermissions(
                        activity!!,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        PERMISSION_WRITE
                    )
                }

            }
        }

        return adapter
    }

    private fun startSelectPhoto(photo: Photo) {
        Glide.with(this)
            .asBitmap()
            .load(photo.large)
            .override(1000)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {

                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val uri = FilesUtil().getImageUri(activity!!, resource)!!
                    startActivity(Intent().apply {
                        action = Intent.ACTION_VIEW
                        setDataAndType(Uri.parse(uri.toString()), "image/*")
                    })
                }
            })
    }

    override fun initLayoutManager(): RecyclerView.LayoutManager {
        val layout = GridLayoutManager(context, 3)

        rvList.addItemDecoration(GridSpacingItemDecoration(3, 10, true))

        return layout
    }

    override fun showPhotos(list: MutableList<Photo>) {
        (adapter as PhotoListAdapter).setDataAll(list)
        showEmptyView(list.isEmpty())
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                RESULT_ADD -> {
                    presenter.clearData()
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        const val RESULT_ADD = 29
        const val PERMISSION_WRITE = 100

        fun getInstance(): PhotoListFragment {
            return PhotoListFragment()
        }
    }
}