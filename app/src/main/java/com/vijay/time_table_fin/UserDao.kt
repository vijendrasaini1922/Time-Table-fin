package com.vijay.time_table_fin

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun addUser(user: User?)

    @Query("SELECT * FROM users_table WHERE username = :username")
    fun getUser(username: String): User
}