package com.example.tracker.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.tracker.model.MedicalRecord

@Dao
interface MedicalRecordDao {
    @Insert
    suspend fun insert(medicalRecord: MedicalRecord)
}