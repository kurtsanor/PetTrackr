package com.example.tracker.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tracker.model.Growth

@Dao
interface GrowthDao {
    @Insert
    suspend fun insert(growth: Growth)

    @Query("SELECT * FROM Growth WHERE petId = :petId")
    suspend fun findAllByPetId(petId: Long): List<Growth>
}