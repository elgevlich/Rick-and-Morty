package com.example.rickandmorty.data.database.dao

import androidx.room.*
import com.example.rickandmorty.data.database.entity.character.CharacterDbModel

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(list: List<CharacterDbModel>)

    @Query(
        "SELECT * FROM item_character WHERE (name LIKE  '%' || :searchName || '%' OR :searchName = '') " +
                "AND (gender LIKE :gender OR :gender = '') " +
                "AND (status LIKE :status OR :status = '') LIMIT :limit OFFSET :offset"
    )
    suspend fun getCharactersPage(
        offset: Int,
        limit: Int,
        searchName: String,
        gender: String,
        status: String
    ): List<CharacterDbModel>

    @Query("DELETE FROM item_character")
    suspend fun deleteAllCharacters()

    @Transaction
    suspend fun refreshCharacters(list: List<CharacterDbModel>) {
        deleteAllCharacters()
        insertCharacter(list)
    }
}