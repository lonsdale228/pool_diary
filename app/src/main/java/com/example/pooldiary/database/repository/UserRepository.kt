package com.example.pooldiary.database.repository

import androidx.lifecycle.LiveData
import com.example.pooldiary.database.data.UserDao
import com.example.pooldiary.models.User


class UserRepository(private val userDao: UserDao){
    val getAllUsers: LiveData<List<User>> = userDao.getAllUsers()

    fun addUser(user: User){
        userDao.addUser(user)
    }
    fun deleteUser(user: User){
        userDao.delete(user)
    }

    fun getUserById(userId: Int): LiveData<User>{
        return userDao.getUserById(userId)
    }
}