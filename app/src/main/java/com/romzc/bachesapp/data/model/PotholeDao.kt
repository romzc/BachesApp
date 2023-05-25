package com.romzc.bachesapp.data.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.romzc.bachesapp.data.entities.PotholeEntity

@Dao
interface PotholeDao {
    @Query("SELECT * FROM Pothole ORDER BY PotId")
    fun getAllPothole() : LiveData<List<PotholeEntity>>

    @Insert
    suspend fun addPothole(pothole: PotholeEntity)
}