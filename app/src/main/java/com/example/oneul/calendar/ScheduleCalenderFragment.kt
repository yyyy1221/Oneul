package com.example.oneul.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.oneul.R
import com.example.oneul.calendar.decorator.OneDayDecorator
import com.example.oneul.databinding.FragmentScheduleCalenderBinding
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.text.SimpleDateFormat

class ScheduleCalenderFragment: Fragment() {
    // 일정 캘린더

    private lateinit var binding: FragmentScheduleCalenderBinding
    private lateinit var sCalendarView:MaterialCalendarView

    private lateinit var oneDayDecorator: OneDayDecorator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentScheduleCalenderBinding.inflate(layoutInflater)

        // Custom Calendar
        sCalendarView = binding.scheduleCalendarView

        // Header
        sCalendarView.setHeaderTextAppearance(R.style.CalendarHeader)
        var sf = SimpleDateFormat("MMMM");

        // WeekDays
        sCalendarView.setWeekDayTextAppearance(R.style.CalendarWeekdays)

        // Date
        sCalendarView.setDateTextAppearance(R.style.CalendarDate)

        // 오늘 날짜 primary 색깔로
        oneDayDecorator = OneDayDecorator()
        sCalendarView.addDecorators(oneDayDecorator)


        return binding.root
    }
}