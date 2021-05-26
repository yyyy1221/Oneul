package com.example.oneul.data

import android.graphics.drawable.Drawable
import com.prolificinteractive.materialcalendarview.CalendarDay

data class DiaryDTO (
    var id: String? = null,
    var date: CalendarDay? = null,
    var images : String? = null,
    var imagesUrl : String? = null,
    var mood: Drawable? = null,
    var diary: String? = null
)