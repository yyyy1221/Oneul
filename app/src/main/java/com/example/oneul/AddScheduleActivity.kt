package com.example.oneul

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Pair
import com.example.oneul.databinding.ActivityAddScheduleBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class AddScheduleActivity : AppCompatActivity() {
    // 일정 추가

    private lateinit var binding : ActivityAddScheduleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageClose.setOnClickListener {
            onBackPressed()
        }

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        val rangePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("")
            .setSelection(
                Pair(MaterialDatePicker.thisMonthInUtcMilliseconds(), MaterialDatePicker.todayInUtcMilliseconds())
            )

        val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(10)
                .setTitleText("")
                .build()

        binding.textDateStart.setOnClickListener {
            datePicker.show(supportFragmentManager, "tag")
        }

        binding.imageCheck.setOnClickListener {
            // TODO: 2021-06-08
        }
    }
}