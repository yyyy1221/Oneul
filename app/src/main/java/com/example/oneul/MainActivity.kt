package com.example.oneul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.oneul.config.MyContext
import com.example.oneul.data.AppDatabase
import com.example.oneul.data.CalenderRepository
import com.example.oneul.databinding.ActivityMainBinding
import com.example.oneul.viewmodel.DiaryViewModel
import com.example.oneul.viewmodel.DiaryViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        val TAG: String = "로그"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var diaryViewModel: DiaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MyContext.setContext(this)

        val app = application as Application
        diaryViewModel = ViewModelProvider(this, DiaryViewModelFactory(app.diaryRepository)).get(DiaryViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}