package data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import data.models.CharacterModel

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(character: CharacterModel): Long

    @Query("SELECT * FROM CharacterModel WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterModel?

    @Query("SELECT * FROM CharacterModel WHERE name = :name")
    suspend fun getCharacterByName(name: String): CharacterModel?

    @Delete
    suspend fun delete(character: CharacterModel)

    @Query("DELETE FROM CharacterModel WHERE id = :id")
    suspend fun deleteById(id: Int): Int

    @Query("SELECT * FROM CharacterModel")
    suspend fun getAllCharacters(): List<CharacterModel>
}