package com.example.oneul

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.oneul.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.activity_drawer.*

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
//            val intent = Intent(context, AddDiaryActivity::class.java)
//            startActivity(intent)
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

        binding.btnOpen.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }
        //총 내가 가지고 있는 메뉴 리스트들의 버튼들을 모두 나열해야함

        binding.menuMyCalandar.setOnClickListener{
            Toast.makeText(context,"my calandar",Toast.LENGTH_SHORT).show()
            //drawer.closeDrawer(GravityCompat.START) //드로어 닫기
        }
        binding.addMenu.setOnClickListener{
            // 캘린더 추가하기
            // 프래그먼트 띄워서 거기서 처리해주기
        }

        binding.circles.setOnCheckedChangeListener{ buttonView, checkedId->
            when(checkedId){
                R.id.circle1-> Toast.makeText(context,"circle1", Toast.LENGTH_SHORT).show()
                R.id.circle2-> Toast.makeText(context,"circle2", Toast.LENGTH_SHORT).show()
                R.id.circle3-> Toast.makeText(context,"circle3", Toast.LENGTH_SHORT).show()
                R.id.circle4-> Toast.makeText(context,"circle4", Toast.LENGTH_SHORT).show()
                R.id.circle5-> Toast.makeText(context,"circle5", Toast.LENGTH_SHORT).show()
                R.id.circle6-> Toast.makeText(context,"circle6", Toast.LENGTH_SHORT).show()
                R.id.circle7-> Toast.makeText(context,"circle7", Toast.LENGTH_SHORT).show()
                R.id.circle8-> Toast.makeText(context,"circle8", Toast.LENGTH_SHORT).show()

            }
        }
        return binding.root
    }

}