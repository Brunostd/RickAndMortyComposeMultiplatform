package datastore

import androidx.datastore.preferences.core.Preferences
import data.models.CharacterModel

interface ArgumentStore {
    suspend fun getArgumentCharacter(): CharacterModel
    suspend fun updateArgumentCharacter(characterModel: CharacterModel): Preferences
}