package com.example.oneul.calendar

import EventDecorator
import android.os.Build
import android.os.Bundle
import android.os.Parcel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.oneul.R
import com.example.oneul.calendar.decorator.OneDayDecorator
import com.example.oneul.databinding.FragmentScheduleCalenderBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.HashSet


class ScheduleCalenderFragment: Fragment() {
    // 일정 캘린더

    private lateinit var binding: FragmentScheduleCalenderBinding
    private lateinit var sCalendarView:MaterialCalendarView

    private lateinit var oneDayDecorator: OneDayDecorator
    private lateinit var eventDecorator: EventDecorator

    private lateinit var dates: HashSet<CalendarDay>

    @RequiresApi(Build.VERSION_CODES.O)
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


        //todo Calendar day list 어떻게 만들지ㅜ

        // 일정 표시되게
        var parcel = Parcel.obtain()
        parcel.writeInt(2021)
        parcel.writeInt(5)
        parcel.writeInt(5)

        //var day = CalendarDay(parcel)

        //dates.add(day)
        //var d = LocalDate.of(2021, 5, 5)
        //var day = CalendarDay(d as Parcel)
        //dates.add(day)

        //var day = CalendarDay(2021,5,5)

        //eventDecorator = EventDecorator(dates)



        //날짜 누르면 일정 볼 수 있게
        sCalendarView.setOnDateChangedListener { widget, date, selected ->
            Toast.makeText(context,date.toString(), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_calenderFragment_to_dailyScheduleFragment)
        }

        return binding.root
    }
}