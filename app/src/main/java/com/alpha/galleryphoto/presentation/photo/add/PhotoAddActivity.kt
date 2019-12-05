package com.alpha.galleryphoto.presentation.photo.add

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.alpha.galleryphoto.R
import com.alpha.galleryphoto.domain.model.data.Photo
import com.alpha.galleryphoto.presentation.common.BaseActivity
import com.alpha.galleryphoto.presentation.common.Layout
import com.alpha.galleryphoto.utils.FilesUtil
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import java.io.File
import java.io.IOException
import javax.inject.Inject


@Layout(id = R.layout.activity_photo_add)
class PhotoAddActivity : BaseActivity(), PhotoAddView {

    @InjectPresenter
    @Inject
    lateinit var presenter: PhotoAddPresenter


    @ProvidePresenter
    fun providePresenter() = presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startSelectPhoto()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_WRITE
            )
        }
    }

    private fun startSelectPhoto() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {

                presenter.file = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }

                presenter.file?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.alpha.galleryphoto.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, RESULT_PHOTO_SELECTED)
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(System.currentTimeMillis().toString(), ".jpg", storageDir)
    }

    override fun endLoadPhoto(photo: Photo) {
        setResult(Activity.RESULT_OK, Intent().also {
            it.putExtra(PHOTO, photo)
        })
        finish()
    }

    override fun showLoadingProgress(show: Boolean) {

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_WRITE) {
            startSelectPhoto()
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                RESULT_PHOTO_SELECTED -> {
                    FilesUtil().normalizeImageForUri(this, Uri.fromFile(presenter.file)).also {
                        presenter.uploadPhoto()
                    }
                }
            }
        } else {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        const val RESULT_PHOTO_SELECTED = 39
        const val PERMISSION_WRITE = 100


        const val PHOTO = "PHOTO"

        fun getIntent(context: Context): Intent = Intent(context, PhotoAddActivity::class.java)
    }

}