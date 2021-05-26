package com.example.oneul.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class CalenderRepository(private val calenderDao: CalenderDao) {

    val allCalenders: Flow<List<Calender>> = calenderDao.getAllCalenders()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(calender: Calender) {
        calenderDao.insert(calender)
    }
}