package com.example.oneul.model

data class CalenderDTO (
    var id: String? = null,
    var name: String? = null,
    var color: Int? = null,
    var dailySchedules: ArrayList<String>? = arrayListOf()
)