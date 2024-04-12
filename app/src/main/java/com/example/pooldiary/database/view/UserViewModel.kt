package com.example.pooldiary.database.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pooldiary.database.UserDatabase
import com.example.pooldiary.database.repository.UserRepository
import com.example.pooldiary.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val getAllUsers: LiveData<List<User>>
    private val repository: UserRepository

    init{
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        getAllUsers = repository.getAllUsers
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

}