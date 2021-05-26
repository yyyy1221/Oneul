package com.example.oneul

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Pair
import com.example.oneul.databinding.ActivityAddScheduleBinding
import com.google.android.material.datepicker.MaterialDatePicker

class AddScheduleActivity : AppCompatActivity() {
    // 일정 추가

    private lateinit var binding : ActivityAddScheduleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        val rangePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("")
            .setSelection(
                Pair(MaterialDatePicker.thisMonthInUtcMilliseconds(), MaterialDatePicker.todayInUtcMilliseconds())
            )

//        val picker = MaterialTimePicker.Builder()
//                .setTimeFormat(TimeFormat.CLOCK_12H)
//                .setHour(12)
//                .setMinute(10)
//                .setTitle("Select Appointment time")
//                .build()

        binding.textDateStart.setOnClickListener {
            datePicker.show(supportFragmentManager, "tag")
        }
    }
}