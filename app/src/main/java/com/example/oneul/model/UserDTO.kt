package com.example.oneul.model

data class UserDTO(
    var id: String? = null,
    var calenders: ArrayList<String>? = arrayListOf(),
    var diaries: ArrayList<String>? = arrayListOf(),
    var moods: ArrayList<String>? = arrayListOf()
)