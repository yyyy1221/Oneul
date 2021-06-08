package com.example.oneul.calendar

//import DiaryDecorator
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.oneul.calendar.decorator.OneDayDecorator
import com.example.oneul.R
import com.example.oneul.calendar.decorator.DiaryDecorator
import com.example.oneul.databinding.FragmentDiaryCalenderBinding
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter
import com.prolificinteractive.materialcalendarview.format.TitleFormatter
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class DiaryCalenderFragment: Fragment() {
    // 일기/기분 캘린더

    private lateinit var binding: FragmentDiaryCalenderBinding
    private lateinit var dCalendarView:MaterialCalendarView

    private lateinit var oneDayDecorator: OneDayDecorator
    private lateinit var diaryDecorator: DiaryDecorator

    @RequiresApi(Build.VERSION_CODES.O)
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
        var monthFormat = DateTimeFormatter.ofPattern("MMMM");
        //var title: TitleFormatter = monthFormat
        //dCalendarView.setTitleFormatter { sf }
        //dCalendarView.setTitleFormatter(monthFormat)

        // WeekDays
        dCalendarView.setWeekDayTextAppearance(R.style.CalendarWeekdays)

        // Date
        dCalendarView.setDateTextAppearance(R.style.CalendarDate)

        // 오늘 날짜 primary 색깔로
        oneDayDecorator = OneDayDecorator()
        //dCalendarView.addDecorators(oneDayDecorator)

        // diary icon 보이게
        diaryDecorator = DiaryDecorator()
        dCalendarView.addDecorators(diaryDecorator)


        //날짜 누르면 일기 볼 수 있게
        dCalendarView.setOnDateChangedListener { widget, date, selected ->
            Toast.makeText(context,date.toString(),Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_calenderFragment_to_dailyDiaryFragment)
        }


        return binding.root
    }
}