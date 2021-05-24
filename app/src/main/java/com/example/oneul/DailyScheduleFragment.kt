package com.example.oneul

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_daily_schedule.view.*
import kotlinx.android.synthetic.main.fragment_schedule.view.*

class DailyScheduleFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view: View = LayoutInflater.from(activity).inflate(R.layout.fragment_daily_schedule, null)
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
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_schedule, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.image_add.setOnClickListener {
                val intent= Intent(holder.itemView.context, AddScheduleActivity::class.java)
                startActivity(holder.itemView.context, intent, null)
            }

            holder.itemView.recycler_schedule.adapter = ScheduleAdapter()
        }

        override fun getItemCount(): Int {
            return 3
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
    }

    class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        }

        override fun getItemCount(): Int {
            return 3
        }
    }
}