package com.example.module_base_sdk.view_holder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BindingViewHolder<VB : ViewBinding, M>(val binding: VB) :
    RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: M)
}