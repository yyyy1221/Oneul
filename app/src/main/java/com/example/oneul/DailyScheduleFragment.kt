package com.example.oneul

import android.app.Dialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.oneul.databinding.FragmentDailyScheduleBinding
import com.example.oneul.databinding.FragmentScheduleBinding
import com.example.oneul.databinding.ItemScheduleBinding
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_daily_schedule.view.*
import kotlinx.android.synthetic.main.fragment_schedule.view.*
import kotlinx.android.synthetic.main.item_schedule.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

class DailyScheduleFragment : DialogFragment() {

    private lateinit var binding: FragmentDailyScheduleBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDailyScheduleBinding.inflate(layoutInflater)
        val view: View = binding.root
        val dialog = Dialog(requireContext(), R.style.Viewpager_dialog)

        initViewpager(view)
        dialog.setContentView(view)
        dialog.setCanceledOnTouchOutside(true)

        return dialog
    }

    fun initViewpager(view: View) {
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pagerWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        view.viewpager_daily_schedule.adapter = DailyScheduleAdapter()
        view.viewpager_daily_schedule.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        view.viewpager_daily_schedule.offscreenPageLimit = 1
        view.viewpager_daily_schedule.currentItem = 1
        view.viewpager_daily_schedule.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }
    }

    class DailyScheduleAdapter : RecyclerView.Adapter<DailyScheduleAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = FragmentScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding.root)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val view = holder.itemView
            view.image_add.setOnClickListener {
                val intent= Intent(view.context, AddScheduleActivity::class.java)
                startActivity(view.context, intent, null)
            }

            val dateformatter: DateFormat = SimpleDateFormat("yyyy년 MM월 dd일")
            val today = Date(System.currentTimeMillis())
            when(position) {
                // 날짜 고치기
                0 -> view.text_date.text = "2021년 06월 10일"
                1 -> view.text_date.text = dateformatter.format(today).toString()
                2 -> view.text_date.text = "2021년 06월 12일"
                3 -> view.text_date.text = "2021년 06월 13일"
                4 -> view.text_date.text = "2021년 06월 14일"
            }

            view.recycler_schedule.adapter = ScheduleAdapter(position%4)
            view.recycler_schedule.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        }

        override fun getItemCount(): Int {
            return 5
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
    }

    class ScheduleAdapter(val p: Int) : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

        inner class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
            val binding = ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ScheduleViewHolder(binding.root)
        }

        override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
            val view = holder.itemView
            if(position%3 == 0) {
                view.text_time_start.visibility = View.VISIBLE
                view.text_time_start.text = "07:00"
                view.text_time_end.visibility = View.VISIBLE
                view.text_time_end.text = "08:00"
                view.text_all_day.visibility = View.GONE
            } else if(position%3 == 1) {
                view.text_schedule_name.text = "운동"
                view.text_time_start.visibility = View.VISIBLE
                view.text_time_start.text = "03:00"
                view.text_time_end.visibility = View.VISIBLE
                view.text_time_end.text = "04:00"
                view.text_all_day.visibility = View.GONE
            } else {
                view.text_schedule_name.text = "종강"
                view.text_time_start.visibility = View.GONE
                view.text_time_end.visibility = View.GONE
                view.text_all_day.visibility = View.VISIBLE
            }
        }

        override fun getItemCount(): Int {
            return p
        }
    }
}