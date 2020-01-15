package com.forestgump.boilerplate.ext

import android.content.Context
import android.content.res.Resources
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.forestgump.boilerplate.R
import kotlin.math.roundToInt

fun Context.color(@ColorRes id: Int) = ContextCompat.getColor(this, id)

fun Context.blue() = color(R.color.blue)
fun Context.white() = color(R.color.white)
fun Context.black() = color(R.color.black)

fun Context.drawable(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)

fun Drawable.tint(@ColorInt color: Int, mode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN) {
    mutate().setColorFilter(color, mode)
}

fun pxToDp(px: Float): Float {
    val densityDpi = Resources.getSystem().displayMetrics.densityDpi.toFloat()
    return px / (densityDpi / 160f)
}

fun dpToPx(dp: Int): Int {
    val density = Resources.getSystem().displayMetrics.density
    return (dp * density).roundToInt()
}
