package com.alpha.galleryphoto.presentation.common

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.alpha.galleryphoto.R
import com.alpha.galleryphoto.presentation.common.list.SimpleEmptyView

abstract class BaseListFragment<D, VH : RecyclerView.ViewHolder> : BaseFragment() {

    var recyclerView: RecyclerView? = null

    protected var adapter: AListAdapter<D, VH>? = null

    private lateinit var layoutManager: RecyclerView.LayoutManager

    var emptyView: SimpleEmptyView? = null


    @get:StringRes
    protected open val emptyViewText: Int = R.string.view_empty_default_text
    @get:DrawableRes
    protected open val emptyViewIcon: Int = R.drawable.ic_add_a_photo


    var dialog: ProgressDialog? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance = true

        var resLayout = R.layout.fragment_default_list


        if (javaClass.getAnnotation(Layout::class.java) is Layout) {
            val layout = javaClass.getAnnotation(Layout::class.java) as Layout
            resLayout = layout.id
        }

        return inflater.inflate(resLayout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView = view.findViewById(R.id.rvList)
        recyclerView?.setHasFixedSize(true)
        layoutManager = initLayoutManager()
        recyclerView?.layoutManager = layoutManager
        progressBar = view.findViewById(R.id.progressBar)

        if (adapter == null)
            adapter = initAdapter()

        dialog = ProgressDialog(activity!!).also {
            it.setTitle("Открытие фотографии")
            it.setMessage("Один момент - скоро все будет")
        }


        recyclerView?.adapter = adapter

        if (emptyView == null) {
            emptyView = view.findViewById<View>(R.id.emptyView) as SimpleEmptyView
            with(emptyView!!) {
                setText(emptyViewText)
                setIcon(emptyViewIcon)
                setVisibility(false)
            }
        }
    }

    fun progressDialog(status: Boolean) {
        if (status)
            dialog?.show()
        else
            dialog?.hide()
    }

    fun clearData() {
        adapter?.clearData()
    }

    protected open fun showEmptyView(show: Boolean) {
        Log.d("trekdeks", show.toString())
        emptyView.setVisibility(show)
    }

    protected abstract fun initAdapter(): AListAdapter<D, VH>

    protected open fun initLayoutManager(): RecyclerView.LayoutManager =
        LinearLayoutManager(recyclerView?.context)


    override fun onDestroy() {
        dialog?.dismiss()
        super.onDestroy()
    }

}
