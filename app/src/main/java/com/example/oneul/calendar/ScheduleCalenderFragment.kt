package com.example.oneul.calendar

//import EventDecorator
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.oneul.R
import com.example.oneul.calendar.decorator.DiaryDecorator
import com.example.oneul.calendar.decorator.EventDecorator
import com.example.oneul.calendar.decorator.OneDayDecorator
import com.example.oneul.databinding.FragmentScheduleCalenderBinding
import com.example.oneul.viewmodel.MainViewModel
import com.example.oneul.viewmodel.MainViewModelFactory
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet


class ScheduleCalenderFragment: Fragment() {
    // 일정 캘린더

    private lateinit var binding: FragmentScheduleCalenderBinding
    private lateinit var sCalendarView:MaterialCalendarView

    private lateinit var oneDayDecorator: OneDayDecorator
    private lateinit var eventDecorator: EventDecorator

    private lateinit var dates: HashSet<CalendarDay>
    private lateinit var mainViewModel: MainViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentScheduleCalenderBinding.inflate(layoutInflater)

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        mainViewModel.currentSchedule.observe(viewLifecycleOwner, Observer {
            val drawable = context?.getDrawable(R.drawable.schedule_task)
            eventDecorator = drawable?.let { EventDecorator(CalendarDay.today(), it) }!!
            sCalendarView.addDecorators(eventDecorator)
        })



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



        //날짜 누르면 일정 볼 수 있게
        sCalendarView.setOnDateChangedListener { widget, date, selected ->
            //Toast.makeText(context,date.toString(), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_calenderFragment_to_dailyScheduleFragment)
        }


        // 운동
        var exerciseList = ArrayList<CalendarDay>()

        for (i:Int in 3..30 step(7)) {
            var calDay = CalendarDay.from(2021, 5, i-1)
            exerciseList.add(calDay)
            if (i>3) {
                calDay = CalendarDay.from(2021, 5, i - 4)
                exerciseList.add(calDay)
            }
        }
        var schedule = context?.getDrawable(R.drawable.schedule_exercise)

        for (calDay in exerciseList) {
            eventDecorator = schedule?.let { EventDecorator(calDay, it) }!!
            sCalendarView.addDecorators(eventDecorator)
        }

        // 여행
        var calDayStart = CalendarDay.from(2021,6,29)
        var calDayEnd = CalendarDay.from(2021,6,30)
        var scheduleLeft = context?.getDrawable(R.drawable.schedule_travel_left)
        var scheduleRight = context?.getDrawable(R.drawable.schedule_travel_right)

        eventDecorator = scheduleLeft?.let { EventDecorator(calDayStart, it) }!!
        sCalendarView.addDecorators(eventDecorator)

        eventDecorator = scheduleRight?.let { EventDecorator(calDayEnd, it) }!!
        sCalendarView.addDecorators(eventDecorator)

        // 종강
        var calDay = CalendarDay.from(2021,6,14)
        schedule = context?.getDrawable(R.drawable.schedule_finish)
        eventDecorator = schedule?.let { EventDecorator(calDay, it) }!!
        sCalendarView.addDecorators(eventDecorator)

        // 기말고사
        var calDay_1 = CalendarDay.from(2021,6,7)
        var calDay_2 = CalendarDay.from(2021,6,8)
        var calDay_3 = CalendarDay.from(2021,6,9)
        var calDay_4 = CalendarDay.from(2021,6,10)

        var schedule_1 = context?.getDrawable(R.drawable.schedule_final_exam_1)
        var schedule_2 = context?.getDrawable(R.drawable.schedule_final_exam_2)
        var schedule_3 = context?.getDrawable(R.drawable.schedule_final_exam_3)
        var schedule_4 = context?.getDrawable(R.drawable.schedule_final_exam_4)

        eventDecorator = schedule_1?.let { EventDecorator(calDay_1, it) }!!
        sCalendarView.addDecorators(eventDecorator)

        eventDecorator = schedule_2?.let { EventDecorator(calDay_2, it) }!!
        sCalendarView.addDecorators(eventDecorator)

        eventDecorator = schedule_3?.let { EventDecorator(calDay_3, it) }!!
        sCalendarView.addDecorators(eventDecorator)

        eventDecorator = schedule_4?.let { EventDecorator(calDay_4, it) }!!
        sCalendarView.addDecorators(eventDecorator)

        // 술
        var alcholList = ArrayList<CalendarDay>()
        calDay = CalendarDay.from(2021,6,2)
        alcholList.add(calDay)
        calDay = CalendarDay.from(2021,5,4)
        alcholList.add(calDay)
        calDay = CalendarDay.from(2021,5,24)
        alcholList.add(calDay)
        calDay = CalendarDay.from(2021,5,29)
        alcholList.add(calDay)

        schedule = context?.getDrawable(R.drawable.schedule_alcohol)
        for (calDay in alcholList) {
            eventDecorator = schedule?.let { EventDecorator(calDay, it) }!!
            sCalendarView.addDecorators(eventDecorator)
        }

        // 과제
        var taskList = ArrayList<CalendarDay>()
        calDay = CalendarDay.from(2021,6,4)
        taskList.add(calDay)
        calDay = CalendarDay.from(2021,6,13)
        taskList.add(calDay)
        for (i:Int in 1..30 step(5)){
            calDay = CalendarDay.from(2021,5,i)
            taskList.add(calDay)
        }

        schedule = context?.getDrawable(R.drawable.schedule_task)
        for (calDay in taskList) {
            eventDecorator = schedule?.let { EventDecorator(calDay, it) }!!
            sCalendarView.addDecorators(eventDecorator)
        }


        // 봉사
        calDay_1 = CalendarDay.from(2021,7,7)
        calDay_2 = CalendarDay.from(2021,7,8)
        calDay_3 = CalendarDay.from(2021,7,9)
        calDay_4 = CalendarDay.from(2021,7,10)

        schedule_1 = context?.getDrawable(R.drawable.schedule_volunteer_1)
        schedule_2 = context?.getDrawable(R.drawable.schedule_volunteer_2)
        schedule_3 = context?.getDrawable(R.drawable.schedule_volunteer_3)
        schedule_4 = context?.getDrawable(R.drawable.schedule_volunteer_4)

        eventDecorator = schedule_1?.let { EventDecorator(calDay_1, it) }!!
        sCalendarView.addDecorators(eventDecorator)

        eventDecorator = schedule_2?.let { EventDecorator(calDay_2, it) }!!
        sCalendarView.addDecorators(eventDecorator)

        eventDecorator = schedule_3?.let { EventDecorator(calDay_3, it) }!!
        sCalendarView.addDecorators(eventDecorator)

        eventDecorator = schedule_4?.let { EventDecorator(calDay_4, it) }!!
        sCalendarView.addDecorators(eventDecorator)



        return binding.root
    }
}