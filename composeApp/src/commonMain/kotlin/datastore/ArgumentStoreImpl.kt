package datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import data.models.CharacterModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ArgumentStoreImpl(
    private val dataStore: DataStore<Preferences>
): ArgumentStore {
    private companion object {
        const val PREFERENCE_NAME = "ArgumentStore"
        const val ARGUMENT_NAME = "argument"
    }

    private val argumentKey = stringPreferencesKey (PREFERENCE_NAME)
    override suspend fun getArgumentCharacter(): CharacterModel {
        try {
            return dataStore.data.map { preferences ->
                Json.decodeFromString<CharacterModel>(preferences[argumentKey].toString())
            }.first()
        } catch (e: Exception) {
            return CharacterModel(name = "Carregando...", image = "")
        }
    }

    override suspend fun updateArgumentCharacter(characterModel: CharacterModel): Preferences = dataStore.edit { preferences ->
        val characterEncode = Json.encodeToString(characterModel)
        preferences[argumentKey] = characterEncode
    }
}

