package com.example.rickandmorty.data.database.dao

import androidx.room.*
import com.example.rickandmorty.data.database.entity.location.LocationDbModel

@Dao
interface LocationDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertLocation(list: List<LocationDbModel>)

	@Query("SELECT * FROM item_location")
	fun getAllLocation(): List<LocationDbModel>

	@Query("DELETE FROM item_location")
	suspend fun deleteAllLocations()

	@Transaction
	suspend fun refreshLocations(list: List<LocationDbModel>){
		deleteAllLocations()
		insertLocation(list)
	}
}