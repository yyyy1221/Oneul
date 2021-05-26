package com.example.oneul.data

data class DailyScheduleDTO (
    var id: String? = null,
    var date: String? = null,
    var schedules: ArrayList<String>? = null
)