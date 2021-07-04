package com.example.module_base_sdk.utils

import android.view.View

fun View.getDimenPx(dimenRes: Int): Int = context.resources.getDimensionPixelSize(dimenRes)

fun View.setOnClickListenerSafe(listener: View.OnClickListener?) {
    this.setOnClickListener(listener?.let { PreventDoubleClickListener { listener.onClick(it) } })
}

private class PreventDoubleClickListener(private val realListener: (View) -> Unit) : View.OnClickListener {

    private var lastClickTime: Long = 0

    override fun onClick(v: View) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > 500) {
            lastClickTime = currentTime
            realListener.invoke(v)
        }
    }
}