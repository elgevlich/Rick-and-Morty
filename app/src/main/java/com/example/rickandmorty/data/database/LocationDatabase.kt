package com.example.rickandmorty.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmorty.data.database.dao.LocationDao
import com.example.rickandmorty.data.database.entity.location.LocationDbModel

@Database(entities = [LocationDbModel::class], version = 3, exportSchema = false)
abstract class LocationDatabase : RoomDatabase() {
    abstract fun getLocationDao(): LocationDao

    companion object {
        private var database: LocationDatabase? = null
        private val ANY = Any()

        fun getMainDatabase(context: Context): LocationDatabase {
            synchronized(ANY) {
                database?.let {
                    return it
                }
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocationDatabase::class.java,
                    "locationDb"
                ).build()
                database = instance
                return instance
            }
        }
    }
}