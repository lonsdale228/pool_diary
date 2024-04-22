package com.example.pooldiary.database.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pooldiary.database.UserDatabase
import com.example.pooldiary.database.repository.ChemistryRepository
import com.example.pooldiary.models.Chemistry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChemistryViewModel(application: Application): AndroidViewModel(application) {

    val getAllChemistry: LiveData<List<Chemistry>>
    private val repository: ChemistryRepository

    init{
        val chemistryDao = UserDatabase.getDatabase(application).chemistryDao()
        repository = ChemistryRepository(chemistryDao)
        getAllChemistry = repository.getAllChemistry
    }

    fun addChemistry(chemistry: Chemistry){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addChemistry(chemistry)
        }
    }

    fun deleteChemistry(chemistry: Chemistry){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteChemistry(chemistry)
        }
    }

}