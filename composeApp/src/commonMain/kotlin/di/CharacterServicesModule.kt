package di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import services.CharacterServices

val characterServicesModule = module {
    singleOf(::CharacterServices)
}