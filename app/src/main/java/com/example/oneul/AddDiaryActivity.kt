package com.example.oneul

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.oneul.config.MyContext
import com.example.oneul.config.MyContext.Companion.context
import com.example.oneul.data.Diary
import com.example.oneul.data.DiaryRepository
import com.example.oneul.databinding.ActivityAddDiaryBinding
import com.example.oneul.viewmodel.DiaryViewModel
import com.example.oneul.viewmodel.DiaryViewModelFactory
import java.util.*

class AddDiaryActivity : AppCompatActivity() {
    // 일기/기분 추가

    private lateinit var binding : ActivityAddDiaryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var today = Calendar.getInstance().time

        Toast.makeText(context,today.toString(), Toast.LENGTH_SHORT).show()


        binding.radioGroupAdd.setOnClickListener {
            var selected = binding.radioGroupAdd.checkedRadioButtonId
        }

        var contents = binding.inputText.toString()
    }
}