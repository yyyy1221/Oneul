package com.example.oneul.model

import androidx.compose.ui.text.input.DeleteAllCommand

data class ScheduleDTO (
    var id: String? = null,
    var title: String? = null,
    var startDate: String ?= null,
    var endDate: String ?= null,
    var startTime: String ?= null,
    var endTime: String ?= null,
    var allDay: Boolean ?= false,
    var location: String ?= null,
    var memo: String ?= null
)