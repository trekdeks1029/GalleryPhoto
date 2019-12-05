package com.alpha.galleryphoto.presentation.common

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import com.alpha.galleryphoto.R
import com.arellomobile.mvp.MvpAppCompatFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


abstract class BaseFragment : MvpAppCompatFragment(), HasSupportFragmentInjector {

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    var progressBar: ProgressBar? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return childFragmentInjector
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progressBar)

    }

    open fun showLoadingProgress(show: Boolean) {
        progressBar.setVisibility(show)
    }

    fun showToast(message: String?) {
        activity.toast(message)
    }

    open fun showToast(@StringRes messageId: Int) {
        activity.toast(getString(messageId))
    }
}