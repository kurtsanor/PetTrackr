package com.example.tracker.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.tracker.model.Growth

@Dao
interface GrowthDao {
    @Insert
    suspend fun insert(growth: Growth)
}