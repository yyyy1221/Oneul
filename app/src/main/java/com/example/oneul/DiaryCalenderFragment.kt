package com.example.oneul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.oneul.Decorator.OneDayDecorator
import com.example.oneul.databinding.FragmentDiaryCalenderBinding
import com.example.oneul.databinding.FragmentScheduleCalenderBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter
import java.text.SimpleDateFormat

class DiaryCalenderFragment: Fragment() {
    // 일정 캘린더, 일기/기분 캘린더

    private lateinit var binding: FragmentDiaryCalenderBinding
    private lateinit var dCalendarView:MaterialCalendarView

    private lateinit var oneDayDecorator: OneDayDecorator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentDiaryCalenderBinding.inflate(layoutInflater)

        // Custom Calendar
        dCalendarView = binding.diaryCalendarView

        // Header
        dCalendarView.setHeaderTextAppearance(R.style.CalendarHeader)
        var sf = SimpleDateFormat("MMMM");

        // WeekDays
        dCalendarView.setWeekDayTextAppearance(R.style.CalendarWeekdays)

        // Date
        dCalendarView.setDateTextAppearance(R.style.CalendarDate)

        // 오늘 날짜 primary 색깔로
        oneDayDecorator = OneDayDecorator()
        dCalendarView.addDecorators(oneDayDecorator)



        return binding.root
    }
}