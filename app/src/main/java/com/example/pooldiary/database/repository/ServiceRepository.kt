package com.example.pooldiary.database.repository

import androidx.lifecycle.LiveData
import com.example.pooldiary.database.data.ServiceDao
import com.example.pooldiary.models.Service


class ServiceRepository(private val serviceDao: ServiceDao){
    val getAllServices: LiveData<List<Service>> = serviceDao.getAllServices()
    val getUserServices: LiveData<List<Service>> = serviceDao.getUserServices()
    fun addService(service: Service){
        serviceDao.insert(service)
    }
    fun deleteService(service: Service){
        serviceDao.delete(service)
    }
}