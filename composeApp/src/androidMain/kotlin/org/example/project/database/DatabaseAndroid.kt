package org.example.project.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.CharacterDataBase
import kotlinx.coroutines.Dispatchers

fun getCharacterDatabase(context: Context): RoomDatabase.Builder<CharacterDataBase> {
    val dbFile = context.getDatabasePath("character.db")
    return Room.databaseBuilder<CharacterDataBase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver()) // Very important
        .setQueryCoroutineContext(Dispatchers.IO)
}