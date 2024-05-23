package screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.models.CharacterModel
import datastore.ArgumentStoreImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val argumentStoreImpl: ArgumentStoreImpl
): ViewModel() {

    private val _character = MutableStateFlow<CharacterModel?>(null)
    val character = _character.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(500L)
            _character.value = argumentStoreImpl.getArgumentCharacter()
        }
    }
}