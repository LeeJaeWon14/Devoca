package com.jeepchief.devoca.model.database

import androidx.room.*

@Dao
interface VocaDAO {
    @Query("SELECT * FROM VocaEntity")
    fun selectVocaAll() : List<VocaEntity>

    @Query("SELECT * FROM VocaEntity WHERE vid = :vid")
    fun selectVoca(vid: Int) : VocaEntity

    @Insert
    fun insertVoca(entity: VocaEntity)

    @Update
    fun updateVoca(entity: VocaEntity)

    @Delete
    fun deleteVoca(entity: VocaEntity)
}