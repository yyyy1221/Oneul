package com.example.oneul

import android.app.Dialog
import android.content.Intent
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
import kotlinx.android.synthetic.main.fragment_daily_schedule.view.*
import kotlinx.android.synthetic.main.fragment_schedule.view.*

class DailyScheduleFragment : DialogFragment() {

    private lateinit var binding: FragmentDailyScheduleBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDailyScheduleBinding.inflate(layoutInflater)
        val view: View = binding.root
        val dialog = Dialog(requireContext(), R.style.viewpager_dialog)

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

            view.recycler_schedule.adapter = ScheduleAdapter()
            view.recycler_schedule.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        }

        override fun getItemCount(): Int {
            return 3
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
    }

    class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

        inner class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
            val binding = ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ScheduleViewHolder(binding.root)
        }

        override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {

        }

        override fun getItemCount(): Int {
            return 3
        }
    }
}