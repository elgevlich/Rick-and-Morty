package com.example.rickandmorty.data.database.dao

import androidx.room.*
import com.example.rickandmorty.data.database.entity.character.CharacterDbModel

@Dao
interface CharacterDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertCharacter(list: List<CharacterDbModel>)

	@Query("SELECT * FROM item_character")
	fun getAllCharacters(): List<CharacterDbModel>

	@Query("DELETE FROM item_character")
	suspend fun deleteAllCharacters()

	@Transaction
	suspend fun refreshCharacters(list: List<CharacterDbModel>){
		deleteAllCharacters()
		insertCharacter(list)
	}
}