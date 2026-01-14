package com.example.tracker.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.tracker.model.Vaccination

@Dao
interface VaccinationDao {
    @Insert
    suspend fun insert(vaccination: Vaccination)
}