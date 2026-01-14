package com.example.tracker.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.tracker.model.Pet

@Dao
interface PetDao {
    @Insert
    suspend fun insert(pet: Pet)
}