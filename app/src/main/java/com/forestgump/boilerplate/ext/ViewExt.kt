package com.forestgump.boilerplate.ext

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

var TextView.drawableStart: Drawable?
    get() = drawables[0]
    set(value) = setDrawables(value, drawableTop, drawableEnd, drawableBottom)

var TextView.drawableTop: Drawable?
    get() = drawables[1]
    set(value) = setDrawables(drawableStart, value, drawableEnd, drawableBottom)

var TextView.drawableEnd: Drawable?
    get() = drawables[2]
    set(value) = setDrawables(drawableStart, drawableTop, value, drawableBottom)

var TextView.drawableBottom: Drawable?
    get() = drawables[3]
    set(value) = setDrawables(drawableStart, drawableTop, drawableEnd, value)

private val TextView.drawables: Array<Drawable?>
    get() = compoundDrawablesRelative

private fun TextView.setDrawables(
    start: Drawable?,
    top: Drawable?,
    end: Drawable?,
    bottom: Drawable?
) {
    setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)
}
