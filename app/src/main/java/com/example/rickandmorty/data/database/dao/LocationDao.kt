package com.example.rickandmorty.data.database.dao

import androidx.room.*
import com.example.rickandmorty.data.database.entity.location.LocationDbModel

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(list: List<LocationDbModel>)

    @Query(
        "SELECT * FROM item_location WHERE (name LIKE  '%' || :searchName || '%' OR :searchName = '') " +
                "AND (type LIKE :type OR :type = '') AND (dimension LIKE :dimension OR :dimension ='') " +
                "LIMIT :limit OFFSET :offset"
    )
    suspend fun getAllLocationPage(
        offset: Int,
        limit: Int,
        searchName: String,
        type: String,
        dimension: String
    ): List<LocationDbModel>

    @Query("DELETE FROM item_location")
    suspend fun deleteAllLocations()

    @Transaction
    suspend fun refreshLocations(list: List<LocationDbModel>) {
        deleteAllLocations()
        insertLocation(list)
    }
}