package com.example.oneul.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class DiaryRepository(private val diaryDao: DiaryDao) {

    val allDiaries: Flow<List<Diary>> = diaryDao.getAllDiary()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(diary: Diary) {
        diaryDao.insert(diary)
    }
}