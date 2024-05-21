package screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import model.ResultsModel
import services.CharacterServices

class HomeViewModel(
    private val characterServices: CharacterServices
): ViewModel() {

    private val _characters = MutableStateFlow<List<ResultsModel>>(listOf())
    val characters = _characters.asStateFlow()

    private val _countPage = MutableStateFlow(1)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.value = characterServices.getCharacters(_countPage.value).results
        }
    }

    fun nextPage(){
        _countPage.value += 1
        viewModelScope.launch(Dispatchers.IO) {
            _characters.value = characterServices.getCharacters(_countPage.value).results
        }
    }

    fun backPage(){
        _countPage.value -= 1
        viewModelScope.launch(Dispatchers.IO) {
            _characters.value = characterServices.getCharacters(_countPage.value).results
        }
    }

}