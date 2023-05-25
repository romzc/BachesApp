package com.romzc.bachesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.romzc.bachesapp.data.entities.AppDatabase
import com.romzc.bachesapp.data.entities.PotholeEntity
import com.romzc.bachesapp.data.entities.UserWithPotholes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PotholeViewModel(application: Application): AndroidViewModel(application) {
    private val repository: PotholeRepository
    private val getAllPothole: LiveData<List<UserWithPotholes>>

    init{
        val potholeDao = AppDatabase.getInstance(application).potholeDao()
        /**************/
        val userDao = AppDatabase.getInstance(application).userDao()
        repository = PotholeRepository(userDao,potholeDao,1)

        getAllPothole = repository.getAllPothole
    }

    fun addPothole(pothole: PotholeEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.addPothole(pothole)
        }
    }
}