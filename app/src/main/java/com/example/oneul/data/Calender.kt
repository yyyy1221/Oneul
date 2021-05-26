package com.example.oneul.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calender_table")
data class Calender (
    @PrimaryKey @ColumnInfo(name = "name")
    var name: String,
    var color: Int,
    //ar dailySchedules: List<String>? = emptyList()
)