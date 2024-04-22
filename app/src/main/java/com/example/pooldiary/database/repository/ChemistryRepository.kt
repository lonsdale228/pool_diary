package com.example.pooldiary.database.repository

import androidx.lifecycle.LiveData
import com.example.pooldiary.database.data.ChemistryDao
import com.example.pooldiary.models.Chemistry


class ChemistryRepository(private val chemistryDao: ChemistryDao){
    val getAllChemistry: LiveData<List<Chemistry>> = chemistryDao.getAllChemistry()
    fun addChemistry(chemistry: Chemistry){
        chemistryDao.addChemistry(chemistry)
    }
    fun deleteChemistry(chemistry: Chemistry){
        chemistryDao.delete(chemistry)
    }
}