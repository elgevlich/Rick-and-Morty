package com.example.rickandmorty.data.database.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmorty.data.database.dao.CharacterDao
import com.example.rickandmorty.data.database.entity.character.CharacterDbModel


@Database(entities = [CharacterDbModel::class], version = 6)
abstract class CharacterDatabase: RoomDatabase() {
	abstract fun getCharacterDao(): CharacterDao
	companion object{
		private var database: CharacterDatabase? = null
		private val ANY = Any()

		fun getMainDatabase(context: Context): CharacterDatabase{
			synchronized(ANY){
				database?.let {
					return it
				}
				val instance = Room.databaseBuilder(
					context.applicationContext,
					CharacterDatabase::class.java,
					"characterDb"
				).build()
				database = instance
				return instance
			}
		}
	}
}