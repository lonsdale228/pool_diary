package com.example.pooldiary.database.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pooldiary.database.UserDatabase
import com.example.pooldiary.database.repository.ServiceRepository
import com.example.pooldiary.models.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ServiceViewModel(application: Application): AndroidViewModel(application) {

    val getAllServices: LiveData<List<Service>>
    val getUserServices: LiveData<List<Service>>
    private val repository: ServiceRepository

    init{
        val serviceDao = UserDatabase.getDatabase(application).serviceDao()
        repository = ServiceRepository(serviceDao)
        getAllServices = repository.getAllServices
        getUserServices = repository.getUserServices
    }

    fun addUser(service: Service){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addService(service)
        }
    }

    fun deleteUser(service: Service){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteService(service)
        }
    }

}