package com.example.blur_select.extansions

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop


fun View.blurSelectExtSetGone(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.blurSelectExtSetVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

fun View.blurSelectExtSetMarginStart(marginStart: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(marginStart, marginTop, marginRight, marginBottom)
    this.layoutParams = menuLayoutParams
}

fun View.blurSelectExtSetMarginTop(marginTop: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(marginLeft, marginTop, marginRight, marginBottom)
    this.layoutParams = menuLayoutParams
}

fun View.blurSelectExtSetMarginEnd(marginEnd: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(marginLeft, marginTop, marginEnd, marginBottom)
    this.layoutParams = menuLayoutParams
}

fun View.blurSelectExtSetMarginBot(marginBot: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(marginLeft, marginTop, marginRight, marginBot)
    this.layoutParams = menuLayoutParams
}

fun View.blurSelectExtSetMargins(marginStart: Int, marginTop: Int, marginEnd: Int, marginBot: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(marginStart, marginTop, marginEnd, marginBot)
    this.layoutParams = menuLayoutParams
}

fun View.blurSelectExtSetPaddingStart(paddingStart: Int) {
    this.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)
}

fun View.blurSelectExtSetPaddingTop(paddingTop: Int) {
    this.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)
}

fun View.blurSelectExtSetPaddingEnd(paddingEnd: Int) {
    this.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)
}

fun View.blurSelectExtSetPaddingBot(paddingBottom: Int) {
    this.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)
}

fun View.blurSelectExtStartAnim(@AnimRes animRes: Int): Animation {
    val anim = AnimationUtils.loadAnimation(this.context, animRes)
    this.startAnimation(anim)
    return anim
}

fun View.blurSelectExtSetHeight(newHeight: Int) {
    val params = this.layoutParams as ViewGroup.LayoutParams
    params.height = newHeight
    this.layoutParams = params
}

fun View.blurSelectExtSetWidth(newWidth: Int) {
    val params = this.layoutParams as ViewGroup.LayoutParams
    params.width = newWidth
    this.layoutParams = params
}