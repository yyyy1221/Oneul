package com.example.oneul

import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.oneul.databinding.FragmentDailyDiaryBinding

class DailyDiaryFragment : Fragment() {
    // 데일리 일기/기분

    private lateinit var binding: FragmentDailyDiaryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDailyDiaryBinding.inflate(layoutInflater)


        return binding.root
    }
}