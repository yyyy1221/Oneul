package com.example.oneul

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.viewpager2.widget.ViewPager2
import com.example.oneul.adapters.DailyScheduleAdapter
import kotlinx.android.synthetic.main.item_daily_schedule.view.*

class DailyScheduleFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view: View = LayoutInflater.from(activity).inflate(R.layout.item_daily_schedule, null)
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

}