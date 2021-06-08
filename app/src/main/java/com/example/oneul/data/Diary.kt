package com.example.oneul.data

import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.*

@Entity(tableName = "diary_table")
data class Diary(
    @PrimaryKey @ColumnInfo(name = "id")
    var id: String,
    var date: String,
    var images : String? = null,
    var imagesUrl : String? = null,
    var mood: Int? = null,
    var diary: String? = null
)