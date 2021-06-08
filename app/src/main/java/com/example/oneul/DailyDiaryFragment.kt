package com.example.oneul

import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.oneul.data.Diary
import com.example.oneul.databinding.FragmentDailyDiaryBinding
import com.example.oneul.viewmodel.DiaryViewModel
import com.example.oneul.viewmodel.DiaryViewModelFactory

class DailyDiaryFragment : Fragment() {
    // 데일리 일기/기분

    private lateinit var binding: FragmentDailyDiaryBinding
    private val diaryViewModel: DiaryViewModel by viewModels{
        val app = activity?.application as Application
        DiaryViewModelFactory(app.diaryRepository)
    }
    private lateinit var diaryList : HashSet<Diary>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDailyDiaryBinding.inflate(layoutInflater)

        diaryViewModel.allDiary.observe(viewLifecycleOwner, Observer { diarys->

        })


        return binding.root
    }



}