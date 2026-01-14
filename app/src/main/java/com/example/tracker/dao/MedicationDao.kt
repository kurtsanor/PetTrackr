package com.example.tracker.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.tracker.model.Medication

@Dao
interface MedicationDao {
    @Insert
    suspend fun insert(medication: Medication)
}