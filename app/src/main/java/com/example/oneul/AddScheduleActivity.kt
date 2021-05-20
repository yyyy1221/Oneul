package com.example.oneul

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.oneul.databinding.ActivityAddScheduleBinding

class AddScheduleActivity : AppCompatActivity() {
    // 일정 추가

    private lateinit var binding : ActivityAddScheduleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityAddScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}