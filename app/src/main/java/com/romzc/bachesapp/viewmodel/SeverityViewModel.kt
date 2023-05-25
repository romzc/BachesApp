package com.romzc.bachesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.romzc.bachesapp.data.entities.AppDatabase
import com.romzc.bachesapp.data.entities.SeverityEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SeverityViewModel(application: Application): AndroidViewModel(application) {
    private val repository: SeverityRepository
    private val getAllSeverity: LiveData<List<SeverityEntity>>

    init{
        val severityDao = AppDatabase.getInstance(application).severityDao()
        repository = SeverityRepository(severityDao)
        getAllSeverity = repository.getAllUSeverity
    }

    fun addSeverity(severity: SeverityEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.addSeverity(severity)
        }
    }
}