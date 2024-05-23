package screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.dao.CharactersDao
import data.models.CharacterModel
import datastore.ArgumentStoreImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import model.ResultsModel
import services.CharacterServices

class HomeViewModel(
    private val characterServices: CharacterServices,
    private val argumentStore: ArgumentStoreImpl
) : ViewModel() {

    private val _characters = MutableStateFlow<List<ResultsModel>>(emptyList())
    val characters = _characters.asStateFlow()

    private var charactersDao: CharactersDao? = null

    private val _countPage = MutableStateFlow(1)

    fun initialize(charactersDao: CharactersDao) {
        this.charactersDao = charactersDao
        fetchCharacters()
    }

    fun nextPage() {
        _countPage.value += 1
        fetchCharacters()
    }

    fun backPage() {
        if (_countPage.value > 1) {
            _countPage.value -= 1
            fetchCharacters()
        }
    }

    fun addFavorite(character: CharacterModel) {
        viewModelScope.launch(Dispatchers.IO) {
            charactersDao?.insert(character)
        }
    }

    private fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            charactersDao?.let { dao ->
                val results = characterServices.getCharacters(_countPage.value).results.map { characterResponse ->
                    characterResponse.apply {
                        isFavorite = dao.getCharacterByName(name) != null
                    }
                }
                _characters.value = results
            }
        }
    }

    fun navigateToDetail(character: ResultsModel) {
        viewModelScope.launch {
            argumentStore.updateArgumentCharacter(CharacterModel(character))
        }
    }
}
