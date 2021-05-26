package com.example.oneul

import android.app.Application
import com.example.oneul.data.AppDatabase
import com.example.oneul.data.CalenderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Application : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getDatabase(this, applicationScope)}
    val repository by lazy { CalenderRepository(database.calenderDao()) }
}