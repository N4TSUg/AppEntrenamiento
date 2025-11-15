package com.example.appentrenamiento.Composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appentrenamiento.Entities.Entrenamiento
import com.example.appentrenamiento.Repositorios.RepositorioEntrenamientos

@Composable
fun PantallaFormulario(
    onCancelar: () -> Unit,
    onGuardar: (Entrenamiento) -> Unit,
    repositorio: RepositorioEntrenamientos
) {
    val fecha = remember { mutableStateOf("") }
    val tipo = remember { mutableStateOf("Fuerza") }
    val nombre = remember { mutableStateOf("") }
    val duracion = remember { mutableStateOf(0) }
    val notas = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        OutlinedTextField(
            value = fecha.value,
            onValueChange = { fecha.value = it },
            label = { Text("Fecha (ej. 2025-11-13)") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Tipo", style = MaterialTheme.typography.labelLarge, modifier = Modifier.padding(top = 8.dp))
        Column {
            RowItemRadio("Fuerza", tipo.value) { tipo.value = it }
            RowItemRadio("Cardio", tipo.value) { tipo.value = it }
        }

        OutlinedTextField(
            value = nombre.value,
            onValueChange = { nombre.value = it },
            label = { Text("Entrenamiento") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )

        OutlinedTextField(
            value = if (duracion.value == 0) "" else duracion.value.toString(),
            onValueChange = { duracion.value = it.toIntOrNull() ?: 0 },
            label = { Text("Duración (min)") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )

        OutlinedTextField(
            value = notas.value,
            onValueChange = { notas.value = it },
            label = { Text("Notas") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )

        Row(modifier = Modifier.padding(top = 12.dp)) {
            Button(onClick = onCancelar) { Text("Cancelar") }
            Button(onClick = {
                val nuevo = repositorio.crearNuevo(
                    fecha = fecha.value,
                    tipo = tipo.value,
                    nombre = nombre.value,
                    duracionMin = duracion.value,
                    notas = notas.value
                )
                onGuardar(nuevo)
            }, modifier = Modifier.padding(start = 8.dp)) {
                Text("Guardar")
            }
        }
    }
}

@Composable
private fun RowItemRadio(option: String, selected: String, onSelect: (String) -> Unit) {
    Row(modifier = Modifier.padding(top = 4.dp)) {
        RadioButton(selected = option == selected, onClick = { onSelect(option) }, colors = RadioButtonDefaults.colors())
        Text(option, modifier = Modifier.padding(start = 8.dp))
    }
}
