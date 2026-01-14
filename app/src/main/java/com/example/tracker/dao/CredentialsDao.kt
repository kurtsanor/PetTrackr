package com.example.tracker.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tracker.model.Credentials

@Dao
interface CredentialsDao {
    @Insert
    suspend fun insert(credentials: Credentials)

    @Query("SELECT 1 FROM Credentials WHERE email = :email LIMIT 1")
    suspend fun existsEmail(email: String): Int?

    @Query("SELECT * FROM Credentials WHERE email = :email")
    suspend fun findByEmail(email: String): Credentials?
}