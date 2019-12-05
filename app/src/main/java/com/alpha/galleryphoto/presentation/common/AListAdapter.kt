package com.alpha.galleryphoto.presentation.common

import androidx.recyclerview.widget.RecyclerView
import android.view.View


abstract class AListAdapter<D, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    var dataSet: MutableList<D> = ArrayList()

    init {
        this.dataSet = ArrayList()
    }

    var onDataClickListener: ((item: D, position: Int) -> Unit)? = null

    override fun getItemCount(): Int {
        return dataSet.size
    }

    open fun setDataAll(data: List<D>) {
        dataSet = data.toMutableList()
        notifyDataSetChanged()
    }

    @Synchronized
    fun clearData() {
        dataSet = ArrayList()
        notifyDataSetChanged()
    }

    open fun createDefaultViewHolder(itemView: View): DefaultViewHolder<D> {
        return DefaultViewHolder<D>(itemView).onClickListen { position ->
            onDataClickListener?.invoke(dataSet[position], position)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: VH, position: Int) {
        bindDefaultViewHolder(holder as DefaultViewHolder<D>, position)
    }

    private fun bindDefaultViewHolder(holder: DefaultViewHolder<D>, position: Int) {
        if (position >= dataSet.size)
            return

        val d = dataSet[position]
        holder.view.bind(d, position)
    }

    fun onDestroy() {
        this.onDataClickListener = null
    }

    @Suppress("UNCHECKED_CAST")
    class DefaultViewHolder<D>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var view: IListItemView<D> = itemView as IListItemView<D>
    }
}
