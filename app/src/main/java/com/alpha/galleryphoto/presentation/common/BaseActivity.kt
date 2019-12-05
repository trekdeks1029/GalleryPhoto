package com.alpha.galleryphoto.presentation.common

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import com.alpha.galleryphoto.R
import com.arellomobile.mvp.MvpAppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : MvpAppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    private var progressBar: ProgressBar? = null

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)


        if (javaClass.getAnnotation(Layout::class.java) is Layout) {
            val layout = javaClass.getAnnotation(Layout::class.java) as Layout
            setContentView(layout.id)
        }

        progressBar = findViewById(R.id.progressBar)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    fun showToast(message: String?) {
        toast(message)
    }

    open fun showToast(@StringRes messageId: Int) {
        toast(getString(messageId))
    }
}