package com.example.oneul

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.oneul.calendar.DiaryCalenderFragment
import com.example.oneul.calendar.ScheduleCalenderFragment
import com.example.oneul.data.AppDatabase
import com.example.oneul.data.Calender
import com.example.oneul.databinding.FragmentMainBinding
import com.example.oneul.viewmodel.MainViewModel
import com.example.oneul.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.item_calender.view.*

class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var scheduleCalendarFm: ScheduleCalenderFragment
    private lateinit var diaryCalendarFm: DiaryCalenderFragment

    private val mainViewModel: MainViewModel by viewModels {
        val app = activity?.application as Application
        MainViewModelFactory(app.calenderRepository, app.diaryRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)

        val adapter = CalenderListAdapter()
        binding.recyclerCalender.adapter = adapter
        binding.recyclerCalender.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        mainViewModel.allCalenders.observe(viewLifecycleOwner, Observer { allCalenders ->
            allCalenders?.let { adapter.submitList(it)}
        })

        binding.buttonDiary.setOnClickListener {
            findNavController().navigate(R.id.action_calenderFragment_to_dailyDiaryFragment)
        }

        binding.buttonSchedule.setOnClickListener {
            findNavController().navigate(R.id.action_calenderFragment_to_dailyScheduleFragment)
        }

        binding.buttonAddDiaryMain.setOnClickListener {
            val intent= Intent(context,AddDiaryActivity::class.java)
            startActivity(intent)
            // 데이터 가져와야하므로 추후에 startResultActivity로 수정해야함
        }

        binding.buttonAddEventMain.setOnClickListener {
            val intent= Intent(context,AddScheduleActivity::class.java)
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

//        binding.menuMyCalandar.setOnClickListener{
//            Toast.makeText(context,"my calandar",Toast.LENGTH_SHORT).show()
//            //drawer.closeDrawer(GravityCompat.START) //드로어 닫기
//        }
        binding.addMenu.setOnClickListener{
            // 캘린더 추가하기
            // 프래그먼트 띄워서 거기서 처리해주기
        }

        binding.circlesLine1.setOnCheckedChangeListener{ buttonView, checkedId->
            when(checkedId){
                R.id.circle1-> Toast.makeText(context,"circle1", Toast.LENGTH_SHORT).show()
                R.id.circle2-> Toast.makeText(context,"circle2", Toast.LENGTH_SHORT).show()
                R.id.circle3-> Toast.makeText(context,"circle3", Toast.LENGTH_SHORT).show()
                R.id.circle4-> Toast.makeText(context,"circle4", Toast.LENGTH_SHORT).show()
                R.id.circle5-> Toast.makeText(context,"circle5", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    class CalenderListAdapter : ListAdapter<Calender, CalenderListAdapter.CalenderViewHolder>(CalenderComparator()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalenderViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_calender, parent, false)
            return CalenderViewHolder(view)
        }

        override fun onBindViewHolder(holder: CalenderViewHolder, position: Int) {
            holder.itemView.btn_myCalandar.text = getItem(position).name
        }

        class CalenderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

        class CalenderComparator : DiffUtil.ItemCallback<Calender>() {
            override fun areItemsTheSame(oldItem: Calender, newItem: Calender): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Calender, newItem: Calender): Boolean {
                return oldItem.name == newItem.name
            }

        }

    }

}