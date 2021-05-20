package com.example.oneul.calendar.decorator

import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.core.app.NotificationCompat.getColor
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.res.ResourcesCompat.getColor
import com.example.oneul.MainActivity.Companion.TAG
import com.example.oneul.R
import com.example.oneul.config.MyContext.Companion.context
import com.google.android.material.color.MaterialColors.getColor
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.*


class OneDayDecorator : DayViewDecorator {

    private var date: CalendarDay

    init{
        date = CalendarDay.today()
    }

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return date != null && day!!.equals(date);
    }



    override fun decorate(view: DayViewFacade) {
        view.addSpan(StyleSpan(Typeface.BOLD));
        view.addSpan(RelativeSizeSpan(1.5f));
        view.addSpan(ForegroundColorSpan(getColor(context,R.color.primary)));

    }


    fun setDate(date: Date?) {
        //this.date = CalendarDay.from(date)
    }
}