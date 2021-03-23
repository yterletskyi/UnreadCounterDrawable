package com.example.unreadcounterdrawable

import android.graphics.*
import android.graphics.drawable.Drawable
import android.text.TextPaint
import kotlin.math.max


class UnseenItemsDrawable(
    private val originalDrawable: Drawable,
    private val unseenNumber: Int
) : Drawable() {

    private val textPaint = TextPaint().apply {
        color = Color.WHITE
        textSize = 10.sp
        textAlign = Paint.Align.CENTER
    }

    private val bgPaint = Paint().apply {
        color = Color.parseColor("#FF3C5A")
    }

    private val textPadding = 2f.dp

    override fun onBoundsChange(bounds: Rect) {
        val myBounds = Rect(0, 0, intrinsicWidth, intrinsicHeight)
        originalDrawable.bounds = myBounds
        super.onBoundsChange(myBounds)
    }

    override fun getIntrinsicHeight(): Int {
        return originalDrawable.intrinsicHeight
    }

    override fun getIntrinsicWidth(): Int {
        return originalDrawable.intrinsicWidth
    }

    override fun draw(canvas: Canvas) {
        originalDrawable.draw(canvas)

        if (unseenNumber > 0) {
            val text = "$unseenNumber"
            val textWidth = textPaint.measureText(text)
            val textHeight = textPaint.descent() - textPaint.ascent()
            val textOffset = textHeight / 2 - textPaint.descent()

            val radius = max(textWidth, textHeight)
            val translationTopRight = 0.3f * radius

            val bounds = RectF(
                intrinsicWidth - radius - textPadding + translationTopRight,
                0f - textPadding - translationTopRight,
                intrinsicWidth.toFloat() + textPadding + translationTopRight,
                radius + textPadding - translationTopRight
            )

            canvas.drawOval(bounds, bgPaint)
            canvas.drawText(text, bounds.centerX(), bounds.centerY() + textOffset, textPaint)
        }
    }

    override fun setAlpha(alpha: Int) {
        textPaint.alpha = alpha
    }

    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT

    override fun setColorFilter(colorFilter: ColorFilter?) {
        textPaint.colorFilter = colorFilter
    }

}