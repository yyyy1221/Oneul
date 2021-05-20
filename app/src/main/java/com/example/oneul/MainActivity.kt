package com.example.oneul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.oneul.config.MyContext
import com.example.oneul.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        val TAG: String = "로그"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MyContext.setContext(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}