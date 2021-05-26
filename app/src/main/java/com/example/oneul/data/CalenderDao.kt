package com.example.oneul.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CalenderDao {
    @Query("SELECT * FROM calender_table")
    fun getAllCalenders() : Flow<List<Calender>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(calender: Calender)

    @Query("DELETE FROM calender_table")
    suspend fun deleteAll()
}