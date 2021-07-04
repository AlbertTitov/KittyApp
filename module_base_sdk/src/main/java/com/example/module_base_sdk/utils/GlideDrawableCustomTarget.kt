package com.example.module_base_sdk.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

open class GlideBitmapCustomTarget : CustomTarget<Bitmap>() {
    override fun onLoadCleared(placeholder: Drawable?) {}
    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {}
    override fun onLoadFailed(errorDrawable: Drawable?) {
        super.onLoadFailed(errorDrawable)
    }
}