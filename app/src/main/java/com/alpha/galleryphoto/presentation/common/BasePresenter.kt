package com.alpha.galleryphoto.presentation.common

import com.alpha.galleryphoto.R
import com.alpha.galleryphoto.domain.model.ErrorViewModel
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.net.UnknownHostException

abstract class BasePresenter<View : IView> : MvpPresenter<View>() {

    private val disposables = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

    protected open fun getViewError(e: Throwable): ErrorViewModel? {
        return when (e) {
            is UnknownHostException -> ErrorViewModel(
                R.drawable.ic_network_wifi,
                R.string.error_network_lost
            )
            else -> null
        }
    }
}