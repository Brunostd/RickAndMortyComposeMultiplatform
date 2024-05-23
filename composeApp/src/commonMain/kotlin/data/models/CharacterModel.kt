package data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import model.ResultsModel

@Serializable
@Entity
data class CharacterModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val image: String
) {
    constructor(resultsModel: ResultsModel) : this(
        name = resultsModel.name,
        image = resultsModel.image
    )
}