package com.example.tracker.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tracker.model.Appointment

@Dao
interface AppointmentDao {
    @Insert
    suspend fun insert(appointment: Appointment)

    @Update
    suspend fun update(appointment: Appointment)

    @Query("DELETE FROM Appointment WHERE id = :id")
    suspend fun deleteById(id: Long)
}