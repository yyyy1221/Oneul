package com.example.oneul

import android.app.Application
import com.example.oneul.data.AppDatabase
import com.example.oneul.data.CalenderRepository
import com.example.oneul.data.DiaryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Application : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getDatabase(this, applicationScope)}
    val calenderRepository by lazy { CalenderRepository(database.calenderDao()) }
    val diaryRepository by lazy { DiaryRepository(database.diaryDao()) }
}