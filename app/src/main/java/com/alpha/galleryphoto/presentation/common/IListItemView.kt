package com.alpha.galleryphoto.presentation.common

interface IListItemView<D> {
    fun bind(item: D, position: Int)
}