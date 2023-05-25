package com.romzc.bachesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.romzc.bachesapp.data.entities.AppDatabase
import com.romzc.bachesapp.data.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    private val repository: UserRepository
    private val getAllUser: LiveData<List<UserEntity>>

    init{
        val userDao = AppDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
        getAllUser = repository.getAllUser
    }

    fun addUser(user: UserEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }

    suspend fun checkUser(email: String, password: String): Boolean {
        return repository.checkUser(email,password)
    }

    suspend fun getUserName(userId: Int): String? {
        return repository.getUserName(userId)?: ""
    }

    suspend fun getUserId(email: String, password: String): Int? {
        return repository.getUserId(email, password) ?: -1
    }
}