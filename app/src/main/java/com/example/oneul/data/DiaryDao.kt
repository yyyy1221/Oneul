package com.example.oneul.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {
    @Query("SELECT * FROM diary_table ORDER BY date ASC")
    fun getAllDiary() : Flow<List<Diary>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(diary: Diary)

    @Update
    fun updateDiary(diary: Diary)

    @Delete
    fun deleteDiary(diary: Diary)

    @Query("DELETE FROM diary_table")
    suspend fun deleteAll()
}