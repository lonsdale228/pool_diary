package com.example.pooldiary.database.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pooldiary.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun addUser(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE) fun updateUser(user: User)

    @Delete fun delete(user: User)

    @Query("SELECT * FROM users ORDER BY name ASC") fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM users JOIN services ON users.id = services.user_id WHERE users.id = :userId") fun getUserById(userId: Int): LiveData<User>

}