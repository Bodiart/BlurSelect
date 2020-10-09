package com.example.extansions

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop


fun View.setGone(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.setVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

fun Boolean?.getVisibility(): Int {
    return if (this == true) View.VISIBLE else View.INVISIBLE
}

fun View.setMarginStart(marginStart: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(marginStart, marginTop, marginRight, marginBottom)
    this.layoutParams = menuLayoutParams
}

fun View.setMarginTop(marginTop: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(marginLeft, marginTop, marginRight, marginBottom)
    this.layoutParams = menuLayoutParams
}

fun View.setMarginEnd(marginEnd: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(marginLeft, marginTop, marginEnd, marginBottom)
    this.layoutParams = menuLayoutParams
}

fun View.setMarginBot(marginBot: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(marginLeft, marginTop, marginRight, marginBot)
    this.layoutParams = menuLayoutParams
}

fun View.setMargins(marginStart: Int, marginTop: Int, marginEnd: Int, marginBot: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(marginStart, marginTop, marginEnd, marginBot)
    this.layoutParams = menuLayoutParams
}

fun View.setPaddingStart(paddingStart: Int) {
    this.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)
}

fun View.setPaddingTop(paddingTop: Int) {
    this.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)
}

fun View.setPaddingEnd(paddingEnd: Int) {
    this.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)
}

fun View.setPaddingBot(paddingBottom: Int) {
    this.setPadding(paddingStart, paddingTop, paddingEnd, paddingBottom)
}

fun View.startAnim(@AnimRes animRes: Int): Animation {
    val anim = AnimationUtils.loadAnimation(this.context, animRes)
    this.startAnimation(anim)
    return anim
}

fun View.setHeight(newHeight: Int) {
    val params = this.layoutParams as ViewGroup.LayoutParams
    params.height = newHeight
    this.layoutParams = params
}

fun View.setWidth(newWidth: Int) {
    val params = this.layoutParams as ViewGroup.LayoutParams
    params.width = newWidth
    this.layoutParams = params
}