package com.example.pooldiary.database.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pooldiary.models.Service
//import com.example.pooldiary.models.ServiceWithUser

@Dao
interface ServiceDao {
//    @Transaction
//    @Query("SELECT * FROM services")
//    fun getServicesWithUsers(): List<ServiceWithUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insert(service: Service)

    @Update(onConflict = OnConflictStrategy.REPLACE) fun update(service: Service)

    @Delete fun delete(service: Service)

    @Query("SELECT * FROM services ORDER BY datetime ASC") fun getAllServices(): LiveData<List<Service>>

    @Query("""
    SELECT 
        services.*
    FROM services 
    JOIN users ON services.user_id = users.id 
    WHERE users.id = :userId 
    ORDER BY services.datetime ASC
""") fun getUserServices(userId: Int): LiveData<List<Service>>

}