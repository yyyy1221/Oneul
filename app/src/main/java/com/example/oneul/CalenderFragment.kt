package com.example.oneul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.oneul.databinding.FragmentCalenderBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter
import kotlinx.android.synthetic.main.fragment_calender.view.*
import java.text.SimpleDateFormat

class CalenderFragment: Fragment() {
    // 일정 캘린더, 일기/기분 캘린더

    private lateinit var binding: FragmentCalenderBinding
    private lateinit var CalendarView:MaterialCalendarView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCalenderBinding.inflate(layoutInflater)

        binding.buttonDiary.setOnClickListener {
            findNavController().navigate(R.id.action_calenderFragment_to_dailyDiaryFragment)
        }

        // Custom Calendar
        CalendarView = binding.calendarView

        // 처음 오늘 날짜 표시되어있음
        CalendarView.setSelectedDate(CalendarDay.today())



        return binding.root
    }
}