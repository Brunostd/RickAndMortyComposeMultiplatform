package screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.dao.CharactersDao
import data.models.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel: ViewModel() {

    private val _favoriteCharacters = MutableStateFlow<List<CharacterModel>>(listOf())
    val favoriteCharacters = _favoriteCharacters.asStateFlow()

    fun initialize(charactersDao: CharactersDao) {
        viewModelScope.launch(Dispatchers.IO) {
            _favoriteCharacters.value = charactersDao.getAllCharacters()
        }
    }
}