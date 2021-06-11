package com.example.oneul

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
import com.example.oneul.data.Diary
import com.example.oneul.databinding.FragmentMainBinding
import com.example.oneul.viewmodel.DiaryViewModel
import com.example.oneul.viewmodel.DiaryViewModelFactory
import com.example.oneul.viewmodel.MainViewModel
import com.example.oneul.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_calender.view.*

class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var scheduleCalendarFm: ScheduleCalenderFragment
    private lateinit var diaryCalendarFm: DiaryCalenderFragment

    private lateinit var diaryViewModel: DiaryViewModel
    private lateinit var mainViewModel: MainViewModel

    private val startForResultDiary = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
//            val diary = result.data!!.getParcelableExtra<Diary>("diary")!!
//            diaryViewModel.insert(diary)
            diaryViewModel.setCurrentDiary(Diary(date = ""))
        }
    }

    private val startForResultSchedule = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            mainViewModel.notifyCurrententSchedule()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)

        diaryViewModel = ViewModelProvider(requireActivity()).get(DiaryViewModel::class.java)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

//        val adapter = CalenderListAdapter()
//        binding.recyclerCalender.adapter = adapter
//        binding.recyclerCalender.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

//        mainViewModel.allCalenders.observe(viewLifecycleOwner, Observer { allCalenders ->
//            allCalenders?.let { adapter.submitList(it)}
//        })

        binding.buttonAddDiaryMain.setOnClickListener {
            val intent= Intent(context,AddDiaryActivity::class.java)
            startForResultDiary.launch(intent)
        }

        binding.buttonAddEventMain.setOnClickListener {
            val intent= Intent(context,AddScheduleActivity::class.java)
            startForResultSchedule.launch(intent)
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
            if(binding.addMenuContent.visibility==View.GONE){
                binding.addMenuContent.visibility=View.VISIBLE
            }
            else{
                binding.addMenuContent.visibility=View.GONE
            }
        }

        var value = 1
        var temp:String="#ffbcaf"
        binding.calandarAddButton.setOnClickListener{
            when(value){
                1 ->  {
                    binding.tempCalandar1.visibility=View.VISIBLE
                    binding.tempCalandar1.setText(input_calandarname.text.toString())
                    val color:Int=binding.circlesLine.checkedRadioButtonId
                    binding.tempCalandar1.setBackgroundColor(Color.parseColor(temp));
                }
                2 ->  {
                    binding.tempCalandar2.visibility=View.VISIBLE
                    binding.tempCalandar2.setText(input_calandarname.text.toString())
                    val color:Int=binding.circlesLine.checkedRadioButtonId
                    binding.tempCalandar2.setBackgroundColor(Color.parseColor(temp));
                }
                3 ->  {
                    binding.tempCalandar3.visibility=View.VISIBLE
                    binding.tempCalandar3.setText(input_calandarname.text.toString())
                    val color:Int=binding.circlesLine.checkedRadioButtonId
                    binding.tempCalandar3.setBackgroundColor(Color.parseColor(temp));
                }
                4 ->  {
                    binding.tempCalandar4.visibility=View.VISIBLE
                    binding.tempCalandar4.setText(input_calandarname.text.toString())
                    val color:Int=binding.circlesLine.checkedRadioButtonId
                    binding.tempCalandar4.setBackgroundColor(Color.parseColor(temp));
                }
                5 ->  {
                    binding.tempCalandar5.visibility=View.VISIBLE
                    binding.tempCalandar5.setText(input_calandarname.text.toString())
                    val color:Int=binding.circlesLine.checkedRadioButtonId
                    binding.tempCalandar5.setBackgroundColor(Color.parseColor(temp));
                }
            }
            value++
            binding.addMenuContent.visibility=View.GONE
        }
        binding.circlesLine.setOnCheckedChangeListener{ buttonView, checkedId->
            when(checkedId){
                R.id.circle1-> temp="#ffbcaf"
                R.id.circle2-> temp="#FDF372"
                R.id.circle3-> temp="#BBDEFB"
                R.id.circle4-> temp="#E1BEE7"
                R.id.circle5-> temp="#FAD59C"
            }
        }
        binding.settingBt.setOnClickListener{
            Toast.makeText(context, "hhh", Toast.LENGTH_SHORT).show()
            binding.minusBt1.visibility=View.VISIBLE
            binding.minusBt2.visibility=View.VISIBLE
            binding.minusBt3.visibility=View.VISIBLE
            binding.minusBt4.visibility=View.VISIBLE
            binding.minusBt5.visibility=View.VISIBLE
        }
        return binding.root
    }

//    class CalenderListAdapter : ListAdapter<Calender, CalenderListAdapter.CalenderViewHolder>(CalenderComparator()) {
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalenderViewHolder {
//            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_calender, parent, false)
//            return CalenderViewHolder(view)
//        }
//
//        override fun onBindViewHolder(holder: CalenderViewHolder, position: Int) {
//            holder.itemView.btn_myCalandar.text = getItem(position).name
//        }
//
//        class CalenderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
//
//        class CalenderComparator : DiffUtil.ItemCallback<Calender>() {
//            override fun areItemsTheSame(oldItem: Calender, newItem: Calender): Boolean {
//                return oldItem === newItem
//            }
//
//            override fun areContentsTheSame(oldItem: Calender, newItem: Calender): Boolean {
//                return oldItem.name == newItem.name
//            }
//
//        }
//
//    }

}