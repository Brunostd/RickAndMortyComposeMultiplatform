package org.example.project

import App
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.database.getCharacterDatabase

class MainActivity : ComponentActivity() {
    companion object {
        private lateinit var appContext: Context
        fun getContext(): Context {
            return appContext
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContext = applicationContext // Atribuindo o contexto da aplicação ao objeto appContext
        val database = getCharacterDatabase(applicationContext)

        setContent {
            App(database)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    //App()
}