package di

import createDataStore
import datastore.ArgumentStoreImpl
import org.koin.dsl.module
import screens.details.DetailsViewModel
import screens.favorite.FavoriteViewModel
import screens.home.HomeViewModel
import services.CharacterServices
import viewModelDefinition

fun appModule() = module {
    single { CharacterServices() }
    single { ArgumentStoreImpl(createDataStore()) }

    viewModelDefinition { HomeViewModel(get(), get()) }
    viewModelDefinition { DetailsViewModel(get()) }
    viewModelDefinition { FavoriteViewModel() }
}