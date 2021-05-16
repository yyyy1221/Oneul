package com.example.oneul

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.oneul.databinding.FragmentMainBinding

class MainFragment: Fragment() {

    private lateinit var binding:FragmentMainBinding

    private lateinit var scheduleCalendarFm:ScheduleCalenderFragment
    private lateinit var diaryCalendarFm:DiaryCalenderFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(layoutInflater)

        binding.buttonDiary.setOnClickListener {
            findNavController().navigate(R.id.action_calenderFragment_to_dailyDiaryFragment)
        }

        binding.buttonSchedule.setOnClickListener {
            findNavController().navigate(R.id.action_calenderFragment_to_dailyScheduleFragment)
        }

        binding.buttonAddEventMain.setOnClickListener {
            var intent= Intent(context,AddScheduleActivity::class.java)
            startActivity(intent)
            // 데이터 가져와야하므로 추후에 startResultActivity로 수정해야함
        }

        // Calendar Fragment
        scheduleCalendarFm = ScheduleCalenderFragment()
        diaryCalendarFm = DiaryCalenderFragment()

        childFragmentManager.beginTransaction().replace(R.id.fragment_calendar_main,scheduleCalendarFm).addToBackStack(null).commit()


        // toggle button to change
        binding.toggleButtonToChangeMain.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.buttonAddDiaryMain.visibility = View.VISIBLE
                binding.buttonAddEventMain.visibility =View.INVISIBLE

                childFragmentManager.beginTransaction().replace(R.id.fragment_calendar_main,diaryCalendarFm).addToBackStack(null).commit()

            }else{
                binding.buttonAddDiaryMain.visibility = View.INVISIBLE
                binding.buttonAddEventMain.visibility =View.VISIBLE

                childFragmentManager.beginTransaction().replace(R.id.fragment_calendar_main, scheduleCalendarFm).addToBackStack(null).commit()

            }
        }




        return binding.root

    }
}