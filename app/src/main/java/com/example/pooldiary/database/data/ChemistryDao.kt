package com.example.pooldiary.database.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pooldiary.models.Chemistry

@Dao
interface ChemistryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun addChemistry(chem: Chemistry)

    @Update(onConflict = OnConflictStrategy.REPLACE) fun updateChemistry(chem: Chemistry)

    @Delete
    fun delete(chem: Chemistry)

    @Query("SELECT * FROM chemistry") fun getAllChemistry(): LiveData<List<Chemistry>>


}