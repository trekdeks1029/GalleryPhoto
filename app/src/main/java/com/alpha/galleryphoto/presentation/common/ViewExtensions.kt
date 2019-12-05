package com.alpha.galleryphoto.presentation.common

import android.content.Context
import android.view.View
import android.widget.Toast
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun Context?.toast(message: CharSequence?) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context?.toast(message: CharSequence?, duration: Int) =
    Toast.makeText(this, message, duration).show()

fun View?.setVisibility(enable: Boolean) {
    this?.visibility = if (enable) View.VISIBLE else View.GONE
}

fun <T> Observable<T>.standartIO() =
    this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun Completable.standartIO() =
    this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <D> AListAdapter.DefaultViewHolder<D>.onClickListen(event: (position: Int) -> Unit): AListAdapter.DefaultViewHolder<D> {
    itemView.setOnClickListener {
        event.invoke(adapterPosition)
    }
    return this
}