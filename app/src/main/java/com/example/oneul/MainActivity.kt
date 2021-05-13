package com.example.oneul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.oneul.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAddEventMain.setOnClickListener {
            intent= Intent(this,AddScheduleActivity::class.java)
            startActivity(intent)
            // 데이터 가져와야하므로 추후에 startResultActivity로 수정해야함
        }
    }
}