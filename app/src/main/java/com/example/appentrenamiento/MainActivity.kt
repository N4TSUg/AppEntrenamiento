package com.example.appentrenamiento

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appentrenamiento.Composables.PantallaFormulario
import com.example.appentrenamiento.Composables.PantallaLista
import com.example.appentrenamiento.Entities.Entrenamiento
import com.example.appentrenamiento.Repositorios.RepositorioEntrenamientos
import com.example.appentrenamiento.ui.theme.AppEntrenamientoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppEntrenamientoTheme {
                val repo = RepositorioEntrenamientos(this)
                val lista = remember { mutableStateListOf<Entrenamiento>() }
                val pantalla = remember { androidx.compose.runtime.mutableStateOf("lista") }

                LaunchedEffect(Unit) {
                    lista.clear()
                    lista.addAll(repo.listar())
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
                        if (pantalla.value == "lista") {
                            PantallaLista(
                                entrenamientos = lista,
                                onAgregar = { pantalla.value = "form" },
                                onVer = {  }
                            )
                        } else {
                            PantallaFormulario(
                                onCancelar = { pantalla.value = "lista" },
                                onGuardar = { nuevo ->
                                    repo.agregar(nuevo)
                                    lista.add(nuevo)
                                    pantalla.value = "lista"
                                },
                                repositorio = repo
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppEntrenamientoTheme {
        Greeting("Android")
    }
}