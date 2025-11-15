package com.example.appentrenamiento.Composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appentrenamiento.Entities.Entrenamiento

@Composable
fun PantallaLista(
    entrenamientos: List<Entrenamiento>,
    onAgregar: () -> Unit,
    onVer: (Entrenamiento) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        Button(onClick = onAgregar, modifier = Modifier.fillMaxWidth()) {
            Text("Agregar entrenamiento")
        }

        LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 8.dp)) {
            items(entrenamientos) { e ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { onVer(e) }) {
                    Row(modifier = Modifier.padding(12.dp)) {
                        Column {
                            Text(text = e.fecha, style = MaterialTheme.typography.bodyLarge)
                            Text(text = "${e.tipo} • ${e.nombre}")
                            Text(text = "${e.duracionMin} min", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
