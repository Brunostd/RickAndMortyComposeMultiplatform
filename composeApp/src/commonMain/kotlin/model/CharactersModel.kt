package model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterModel(
    val results: List<ResultsModel>)

@Serializable
data class ResultsModel(
    val name: String
)