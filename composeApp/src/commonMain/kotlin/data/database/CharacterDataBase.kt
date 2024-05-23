package data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import data.dao.CharactersDao
import data.models.CharacterModel

@Database(entities = [CharacterModel::class], version = 2)
abstract class CharacterDataBase: RoomDatabase() {
    abstract fun characterDao(): CharactersDao
}