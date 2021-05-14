package com.example.oneul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.oneul.databinding.FragmentScheduleCalenderBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView


class ScheduleCalenderFragment: Fragment() {
    // 일정 캘린더, 일기/기분 캘린더

    private lateinit var binding: FragmentScheduleCalenderBinding
    //private lateinit var calendarView:MaterialCalendarView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view= inflater.inflate(R.layout.fragment_schedule_calender,container,false)

        // Custom Calendar
        //var calendarView = binding.scheduleCalendarView

        // 처음 오늘 날짜 표시되어있음
        //binding.scheduleCalendarView.setSelectedDate(CalendarDay.today())



        return view
    }
}