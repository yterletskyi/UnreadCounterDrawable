package com.example.unreadcounterdrawable

import android.content.res.Resources
import android.util.TypedValue
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

val Int.dp: Int get() = (Resources.getSystem().displayMetrics.density * this).toInt()

val Float.dp: Float get() = (Resources.getSystem().displayMetrics.density * this)

inline val Float.sp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )

inline val Int.sp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )

fun Float.asPercentsFrom(maxValue: Float): Int = ((this / maxValue) * 100f).roundToInt()

inline val Float?.orZero: Float get() = if (this ?: 0f < 0) 0f else this ?: 0f

fun Long.toVideoDurationString(includeHoursZeros: Boolean = false): String {
    val hours: Long = TimeUnit.MILLISECONDS.toHours(this)
    val minutes: Long = TimeUnit.MILLISECONDS.toMinutes(this) % 60
    val seconds: Long = TimeUnit.MILLISECONDS.toSeconds(this) % 60

    return if (hours == 0L && !includeHoursZeros) String.format("%02d:%02d", minutes, seconds)
    else String.format("%02d:%02d:%02d", hours, minutes, seconds)
}