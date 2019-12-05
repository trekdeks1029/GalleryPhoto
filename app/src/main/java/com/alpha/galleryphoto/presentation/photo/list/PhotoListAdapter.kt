package com.alpha.galleryphoto.presentation.photo.list

import android.view.ViewGroup
import com.alpha.galleryphoto.domain.model.data.Photo
import com.alpha.galleryphoto.presentation.common.ABaseView
import com.alpha.galleryphoto.presentation.common.AListAdapter


class PhotoListAdapter :
    AListAdapter<Photo, AListAdapter.DefaultViewHolder<Photo>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DefaultViewHolder<Photo> {
        val context = parent.context

        val view: ABaseView = PhotoGridItemView(
            context
        )

        return createDefaultViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }
}