package com.example.oneul.calendar

//import DiaryDecorator
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.oneul.Application
import com.example.oneul.calendar.decorator.OneDayDecorator
import com.example.oneul.R
import com.example.oneul.calendar.decorator.Diary2Decorator
import com.example.oneul.calendar.decorator.DiaryDecorator
import com.example.oneul.data.Diary
import com.example.oneul.databinding.FragmentDiaryCalenderBinding
import com.example.oneul.viewmodel.DiaryViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import kotlinx.android.synthetic.main.fragment_daily_diary.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DiaryCalenderFragment: Fragment() {
    // 일기/기분 캘린더

    private lateinit var binding: FragmentDiaryCalenderBinding
    private lateinit var dCalendarView:MaterialCalendarView

    private lateinit var oneDayDecorator: OneDayDecorator
    private lateinit var diaryDecorator: DiaryDecorator
    private lateinit var diary2Decorator:Diary2Decorator

    private lateinit var diaryViewModel: DiaryViewModel

    private var dateList : ArrayList<Diary> = ArrayList<Diary>()
    private var calenderdayList : ArrayList<CalendarDay> = ArrayList<CalendarDay>()
    private var moodList = mutableListOf<Drawable>()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val app = activity?.application as Application

        diaryViewModel = ViewModelProvider(requireActivity()).get(DiaryViewModel::class.java)

        binding = FragmentDiaryCalenderBinding.inflate(layoutInflater)

        // Custom Calendar
        dCalendarView = binding.diaryCalendarView

        // Header
        dCalendarView.setHeaderTextAppearance(R.style.CalendarHeader)
        var monthFormat = DateTimeFormatter.ofPattern("MMMM");
        //var title: TitleFormatter = monthFormat
        //dCalendarView.setTitleFormatter { sf }
        //dCalendarView.setTitleFormatter(monthFormat)

        // WeekDays
        dCalendarView.setWeekDayTextAppearance(R.style.CalendarWeekdays)

        // Date
        dCalendarView.setDateTextAppearance(R.style.CalendarDate)


        // Mood list 저장
        moodList.add(context?.getDrawable(R.drawable.joy)!!)
        moodList.add(context?.getDrawable(R.drawable.tired)!!)
        moodList.add(context?.getDrawable(R.drawable.angry)!!)
        moodList.add(context?.getDrawable(R.drawable.enjoy)!!)
        moodList.add(context?.getDrawable(R.drawable.gloom)!!)
        moodList.add(context?.getDrawable(R.drawable.just)!!)
        moodList.add(context?.getDrawable(R.drawable.sad)!!)


        // 4월
        for (i:Int in 1..30 step(6)){
            var new = CalendarDay.from(2021,4,i)
            calenderdayList.add(new)

            new = CalendarDay.from(2021, 4, i + 4)
            calenderdayList.add(new)

        }

        // 5월
        for (i:Int in 1..30 step(4)){
            var new = CalendarDay.from(2021,5,i)
            calenderdayList.add(new)
            new = CalendarDay.from(2021,5,i+1)
            calenderdayList.add(new)
        }

        // 6월
        for (i:Int in 1..15 step(3)){
            var new = CalendarDay.from(2021,6,i)
            calenderdayList.add(new)
        }
        calenderdayList.add(CalendarDay.from(2021,6,14))

        // 감정 + 날짜 calendar에 전달
        var i=0
        for (calDay in calenderdayList) {
            var mood = moodList[i++%7]
            diary2Decorator = Diary2Decorator(calDay,mood)
            dCalendarView.addDecorators(diary2Decorator)
        }

        // 오늘 날짜 primary 색깔로
        oneDayDecorator = OneDayDecorator()
        dCalendarView.addDecorators(oneDayDecorator)



        diaryViewModel.currentDiary.observe(viewLifecycleOwner, Observer { diary ->
            diaryDecorator = DiaryDecorator()
            // diary icon 보이게
            dCalendarView.addDecorators(diaryDecorator)
        })



        //날짜 누르면 일기 볼 수 있게
        dCalendarView.setOnDateChangedListener { widget, date, selected ->
            val diary = Diary(date = date.toString())
            diaryViewModel.setCurrentDiary(diary)

            Toast.makeText(context,date.toString(),Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_calenderFragment_to_dailyDiaryFragment)
        }


        return binding.root
    }
}