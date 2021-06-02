package com.example.oneul.calendar.decorator

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.style.LineBackgroundSpan

class MoodSpan(moodIcon:Bitmap) : LineBackgroundSpan {

    private var span = moodIcon;

    override fun drawBackground(
        canvas: Canvas,
        paint: Paint,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        text: CharSequence,
        start: Int,
        end: Int,
        lineNumber: Int
    ) {
        canvas.setBitmap(span)
    }
}