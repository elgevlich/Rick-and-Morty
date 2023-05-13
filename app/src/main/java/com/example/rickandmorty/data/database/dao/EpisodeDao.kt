package com.example.rickandmorty.data.database.dao

import androidx.room.*
import com.example.rickandmorty.data.database.entity.episode.EpisodeDbModel

@Dao
interface EpisodeDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertEpisode(list: List<EpisodeDbModel>)

	@Query("SELECT * FROM item_episode")
	fun getAllEpisodes(): List<EpisodeDbModel>

	@Query("DELETE FROM item_episode")
	suspend fun deleteAllEpisodes()

	@Transaction
	suspend fun refreshEpisodes(list: List<EpisodeDbModel>){
		deleteAllEpisodes()
		insertEpisode(list)
	}
}