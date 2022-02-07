package com.example.webunihw_eatingplaces.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.webunihw_eatingplaces.model.db.User


@Dao
interface UserDAO {
    @Query("SELECT * FROM user")
    fun getUsers(): LiveData<List<User>>

    @Insert
    suspend fun insertUser(user: User) : Long

    @Insert
    fun insert(vararg grades: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteAllUser()

    @Update
    suspend fun updateUser(user: User)
}