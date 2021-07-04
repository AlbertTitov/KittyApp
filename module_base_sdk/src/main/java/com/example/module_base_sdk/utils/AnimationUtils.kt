package com.example.module_base_sdk.utils

import android.animation.Animator

inline fun Animator.addStartListener(crossinline listener: (Animator?) -> Unit) {
    addListener(object : BriefAnimatorListener {
        override fun onAnimationStart(animation: Animator?) {
            listener.invoke(animation)
        }
    })
}

interface BriefAnimatorListener : Animator.AnimatorListener {
    override fun onAnimationRepeat(animation: Animator?) {}
    override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
        this.onAnimationEnd(animation)
    }

    override fun onAnimationEnd(animation: Animator?) {}
    override fun onAnimationCancel(animation: Animator?) {}
    override fun onAnimationStart(animation: Animator?, isReverse: Boolean) {
        this.onAnimationStart(animation)
    }

    override fun onAnimationStart(animation: Animator?) {}
}