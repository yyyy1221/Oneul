package com.example.oneul.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserDTO(
    @PrimaryKey @ColumnInfo(name = "user")
    var id: String? = null,
    var calenders: ArrayList<String>? = arrayListOf(),
    var diaries: ArrayList<String>? = arrayListOf(),
    var moods: ArrayList<String>? = arrayListOf()
)