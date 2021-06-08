package com.example.oneul.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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