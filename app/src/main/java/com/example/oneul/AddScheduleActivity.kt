package com.example.oneul

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Pair
import androidx.fragment.app.FragmentManager
import com.example.oneul.databinding.ActivityAddScheduleBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddScheduleActivity : AppCompatActivity() {
    // 일정 추가

    private lateinit var binding : ActivityAddScheduleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dateformatter: DateFormat = SimpleDateFormat("yyyy.MM.dd")
        val timeFormatter: DateFormat = SimpleDateFormat("hh:mm")

        val binding = ActivityAddScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textDateStart.text = dateformatter.format(MaterialDatePicker.todayInUtcMilliseconds()).toString()
        binding.textDateEnd.text = dateformatter.format(MaterialDatePicker.todayInUtcMilliseconds()).toString()
        binding.textTimeStart.text = timeFormatter.format(MaterialDatePicker.todayInUtcMilliseconds()).toString()
        binding.textTimeEnd.text = timeFormatter.format(MaterialDatePicker.todayInUtcMilliseconds()).toString()

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
                .setTitleText("")
                .build()

        binding.textDateStart.setOnClickListener {
            datePicker.show(supportFragmentManager, "tag")
        }

        binding.textTimeStart.setOnClickListener {
            timePicker.show(supportFragmentManager, "tag")
        }

        datePicker.addOnPositiveButtonClickListener {
            binding.textDateStart.text = dateformatter.format(datePicker.selection).toString()
        }

        timePicker.addOnPositiveButtonClickListener {
            binding.textTimeStart.text = timePicker.hour.toString()+":"+timePicker.minute.toString()
        }

        binding.switchAllDay.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                binding.textTimeStart.visibility = View.GONE
                binding.textTimeEnd.visibility = View.GONE
            } else {
                binding.textTimeStart.visibility = View.VISIBLE
                binding.textTimeEnd.visibility = View.VISIBLE
            }
        }

        binding.imageCheck.setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra("schedule", 1))
            finish()
        }
    }
}