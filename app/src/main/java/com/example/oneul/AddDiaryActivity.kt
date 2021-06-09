package com.example.oneul

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.oneul.config.MyContext.Companion.context
import com.example.oneul.data.Diary
import com.example.oneul.databinding.ActivityAddDiaryBinding
import java.util.*

class AddDiaryActivity : AppCompatActivity() {
    // 일기/기분 추가

    private lateinit var binding : ActivityAddDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddDiaryBinding.inflate(layoutInflater)

        var today = Calendar.getInstance().time
        Toast.makeText(context,today.toString(), Toast.LENGTH_SHORT).show()
        binding.tvDateAdd.text = today.toString()

        var mood: Int?= null
        binding.radioGroupAdd.setOnClickListener {
            var selected = binding.radioGroupAdd.checkedRadioButtonId
            when(selected) {
                R.id.radio_button_joy -> mood = R.drawable.joy
                R.id.radio_button_enjoy -> mood = R.drawable.enjoy
                R.id.radio_button_gloom -> mood = R.drawable.gloom
                R.id.radio_button_tired -> mood = R.drawable.tired
                R.id.radio_button_angry -> mood = R.drawable.angry
                R.id.radio_button_just -> mood = R.drawable.just
            }
        }
        var contents: String?= null
        binding.inputText.doAfterTextChanged {
            contents = binding.inputText.toString()
        }

        binding.imageCheck.setOnClickListener {
            val diary = Diary(date = today.toString(), mood = mood, diary = contents)
            setResult(RESULT_OK, Intent().putExtra("diary", diary))
            finish()
        }

        setContentView(binding.root)
    }
}