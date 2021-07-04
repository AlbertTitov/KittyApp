package com.example.module_base_sdk.utils

import android.animation.ObjectAnimator
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.Transition

fun ImageView.loadImage(
    url: String,
    roundedFrameCorners: Int = 0,
    successLoad: (() -> Unit?)? = null,
    @DrawableRes errorRes: Int? = null,
    errorLoad: (() -> Unit)? = null
) {

    val animator = ObjectAnimator.ofFloat(this, View.ALPHA, 0f, 1f).apply {
        duration = 300
    }

    Glide.with(context).apply {
        clear(this@loadImage)

        asBitmap().load(url)
            .into(object : GlideBitmapCustomTarget() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    /**
                     *  Использовал здесь RoundedBitmapDrawableFactory для того, чтобы задать подготовленному Bitmap'у
                     *  смасштабированный радиус скругления,
                     *
                     *  не использовал RequestOptions().transform(CenterCrop(), RoundedCorners(roundedFrameCorners)),
                     *  поскольку для картинок разных размеров при константном радиусе скругления визуально получаются разные радиусы,
                     *  радиус надо смасштабировать под размер картинки в пикселях.
                     *
                     *  А ещё по идее надо бы ресайзить тяжёлые картинки - пока не сделал.
                     *
                     * **/
                    val roundedDrawable =
                        RoundedBitmapDrawableFactory.create(context.resources, resource)
                    roundedDrawable.cornerRadius =
                        roundedFrameCorners * resource.height.toFloat() / IMAGE_CORNER_DIVIDER

                    if (this@loadImage.drawable != null) {
                        this@loadImage.setImageDrawable(roundedDrawable)
                        successLoad?.invoke()
                    } else {
                        animator.apply {
                            addStartListener {
                                this@loadImage.setImageDrawable(roundedDrawable)
                                successLoad?.invoke()
                            }
                            start()
                        }
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    errorLoad?.invoke()
                    if (errorRes != null) {
                        this@loadImage.setImageResource(errorRes)
                    }
                }
            })
    }
}

/** Условный делитель для расчёта смасштабированного радиуса закругления **/
private const val IMAGE_CORNER_DIVIDER = 400