package com.example.oneul

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.oneul.databinding.ActivityAddDiaryBinding

class AddDiaryActivity : AppCompatActivity() {
    // 일기/기분 추가

    private lateinit var binding : ActivityAddDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}