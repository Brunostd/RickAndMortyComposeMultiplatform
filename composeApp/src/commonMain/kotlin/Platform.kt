import androidx.lifecycle.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module

val platformModule = module {
    singleOf(::Platform)
}

expect class Platform() {
    val name: String
}

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient

expect inline fun <reified T : ViewModel> Module.viewModelDefinition(
    qualifier: Qualifier? = null,
    noinline definition: Definition<T>
): KoinDefinition<T>