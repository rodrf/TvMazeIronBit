package com.universe.tvmaze.core.utils

import android.content.Context
import android.util.TypedValue

/**
 * TODO
 *
 * @param context
 * @return dp to pixels
 */
fun Int.dpToPx(context: Context): Int {
    val metrics = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics).toInt()
}