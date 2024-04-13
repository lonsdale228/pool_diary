package com.example.pooldiary.database.repository

import androidx.lifecycle.LiveData
import com.example.pooldiary.database.data.ServiceDao
import com.example.pooldiary.models.Service


class ServiceRepository(private val serviceDao: ServiceDao){
    val getAllServices: LiveData<List<Service>> = serviceDao.getAllServices()
    fun addService(service: Service){
        serviceDao.insert(service)
    }
    fun deleteService(service: Service){
        serviceDao.delete(service)
    }
    fun getUserServices(userId: Int): LiveData<List<Service>> {
        return serviceDao.getUserServices(userId)
    }
}