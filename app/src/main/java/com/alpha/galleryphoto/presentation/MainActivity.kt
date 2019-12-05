package com.alpha.galleryphoto.presentation

import android.os.Bundle
import com.alpha.galleryphoto.R
import com.alpha.galleryphoto.presentation.common.BaseActivity
import com.alpha.galleryphoto.presentation.common.Layout
import com.alpha.galleryphoto.presentation.photo.list.PhotoListFragment

@Layout(id = R.layout.activity_default)
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null)
            supportFragmentManager.beginTransaction().replace(R.id.fragment, PhotoListFragment.getInstance()).commit()
    }

}