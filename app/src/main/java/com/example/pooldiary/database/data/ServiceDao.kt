package com.example.pooldiary.database.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pooldiary.models.Service

@Dao
interface ServiceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insert(service: Service)

    @Update(onConflict = OnConflictStrategy.REPLACE) fun update(service: Service)

    @Delete fun delete(service: Service)

    @Query("SELECT * FROM services ORDER BY datetime ASC") fun getAllServices(): LiveData<List<Service>>

    @Query("SELECT * FROM services JOIN users ON services.user_id = users.id WHERE services.user_id = :userId ORDER BY datetime ASC") fun getUserServices(userId: Int): LiveData<List<Service>>

}