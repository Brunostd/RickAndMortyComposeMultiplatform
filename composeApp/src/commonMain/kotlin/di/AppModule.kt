package di

import screens.home.HomeViewModel
import org.koin.dsl.module
import services.CharacterServices
import viewModelDefinition

fun appModule() = module {
    single { CharacterServices() }

    viewModelDefinition { HomeViewModel(get()) }
}