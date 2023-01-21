package com.jeepchief.devoca.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [VocaEntity::class], version = 1, exportSchema = true)
abstract class DevocaDatabase : RoomDatabase() {
    abstract fun getVocaDAO(): VocaDAO

    companion object {
        private var instance: DevocaDatabase? = null
        @Synchronized
        fun getInstance(context: Context) : DevocaDatabase {
            instance?.let {
                return it
            } ?: run {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    DevocaDatabase::class.java,
                    "Devoca.db"
                ).build()
                return instance!!
            }
        }
    }
}