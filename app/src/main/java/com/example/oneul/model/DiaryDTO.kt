package com.example.oneul.model

data class DiaryDTO (
    var id: String? = null,
    var date: String? = null,
    var images : String? = null,
    var imagesUrl : String? = null,
    var mood: Int? = null,
    var diary: String? = null
)