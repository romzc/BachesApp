package com.romzc.bachesapp.data.model
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.romzc.bachesapp.data.entities.PotholeAndSeverity
import com.romzc.bachesapp.data.entities.PotholeEntity
import com.romzc.bachesapp.data.entities.SeverityEntity

interface SeverityDao {
    @Insert
    suspend fun insertPothole(pothole: PotholeEntity)

    @Insert
    suspend fun insertSeverity(severityEntity: SeverityEntity)

    @Insert
    suspend fun addSeverity(severityEntity: SeverityEntity)

    @Transaction
    @Query("SELECT * FROM Pothole WHERE PotId = :potId")
    suspend fun getPotholeAndSeverity(potId: Int): List<PotholeAndSeverity>
}