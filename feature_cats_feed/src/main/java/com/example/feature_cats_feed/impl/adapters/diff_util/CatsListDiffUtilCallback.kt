package com.example.feature_cats_feed.impl.adapters.diff_util

import androidx.recyclerview.widget.DiffUtil
import com.example.data.domain.model.CatEntity

class CatsListDiffUtilCallback(
    private val oldItems: List<CatEntity>,
    private val newItems: List<CatEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldItems[oldItemPosition].id == newItems[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldItems[oldItemPosition].isFavourite == newItems[newItemPosition].isFavourite


}