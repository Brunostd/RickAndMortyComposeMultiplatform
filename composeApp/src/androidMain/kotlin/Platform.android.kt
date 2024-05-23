import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import okio.Path
import okio.Path.Companion.toPath
import org.example.project.MainActivity
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import java.io.File
import java.util.concurrent.TimeUnit

actual class Platform actual constructor() {
    actual val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(OkHttp) {
    config(this)

    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        })
    }

    engine {
        config {
            retryOnConnectionFailure(true)
            connectTimeout(0, TimeUnit.SECONDS)
        }
    }
}

actual inline fun <reified T : ViewModel> Module.viewModelDefinition(
    qualifier: Qualifier?,
    noinline definition: Definition<T>,
): KoinDefinition<T> = viewModel(qualifier = qualifier, definition = definition)

internal const val dataStoreFileName = "dice.preferences_pb"

@RequiresApi(Build.VERSION_CODES.O)
actual fun createDataStore(): DataStore<Preferences> {
    val context = MainActivity.getContext()
    if (context == null) {
        throw IllegalStateException("You must provide context for Android")
    }

    val path = context.filesDir.resolve(dataStoreFileName).absolutePath.toPath()

    return PreferenceDataStoreFactory.createWithPath(
        produceFile = {path}
    )
}


