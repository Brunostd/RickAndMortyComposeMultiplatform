import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.CharacterDataBase
import data.database.instantiateImpl // This shall show error, ignore it
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSHomeDirectory // This shall too in Windows.

fun getUserDatabase(): RoomDatabase.Builder<CharacterDataBase> {
    val dbFile = NSHomeDirectory() + "/character.db"
    return Room.databaseBuilder<CharacterDataBase>(
        name = dbFile,
        factory = { CharacterDataBase::class.instantiateImpl() } // This too will show error
    )
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver()) // Very important
        .setQueryCoroutineContext(Dispatchers.IO)
}