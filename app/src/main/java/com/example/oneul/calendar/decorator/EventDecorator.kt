package com.example.oneul.calendar.decorator

import android.text.style.ForegroundColorSpan
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.oneul.R
import com.example.oneul.config.MyContext
import com.example.oneul.config.MyContext.Companion.context
import com.example.oneul.data.Diary
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class EventDecorator:DayViewDecorator {
    private lateinit var date: CalendarDay

    init {
        date = CalendarDay.today()
    }

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        Toast.makeText(MyContext.context,date.toString(), Toast.LENGTH_SHORT).show()
        //Log.d(MainActivity.TAG,"EventDecorator - shouldDecorate() called - "+date.toString())
        return date != null && day!!.equals(date)
    }

    override fun decorate(view: DayViewFacade?) {
        context.getDrawable(R.drawable.exam)?.let { view!!.setBackgroundDrawable(it) }
        view!!.addSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    MyContext.context,
                    R.color.transparent_blak))
        );
    }
}