package com.example.oneul.calendar.decorator

import android.graphics.drawable.Drawable
import android.os.Build
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.oneul.MainActivity.Companion.TAG
import com.example.oneul.R
import com.example.oneul.config.MyContext
import com.example.oneul.config.MyContext.Companion.context
import com.example.oneul.data.Diary
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

@RequiresApi(Build.VERSION_CODES.O)
class Diary2Decorator(currentDay:CalendarDay, mood:Drawable):DayViewDecorator {

    private var myDay = currentDay
    private var drawable:Drawable

    init {
        drawable = mood
    }

    override fun shouldDecorate(day: CalendarDay?): Boolean {

        return day == myDay
    }

    override fun decorate(view: DayViewFacade?) {

        view!!.setBackgroundDrawable(drawable)
        view!!.addSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    context,
                    R.color.transparent_full))
        )


    }
}