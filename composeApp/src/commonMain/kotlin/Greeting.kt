import io.ktor.client.call.body
import io.ktor.client.request.get
import model.CharacterModel

class Greeting {
    private val platform = Platform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    suspend fun getCharacters(page: Int): CharacterModel? {
        val client = httpClient()
        val result: CharacterModel = client.get("https://rickandmortyapi.com/api/character/?page=${page}").body()
        return result
    }
}