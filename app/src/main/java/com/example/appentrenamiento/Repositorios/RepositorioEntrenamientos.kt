package com.example.appentrenamiento.Repositorios

import android.content.Context
import com.example.appentrenamiento.Entities.Entrenamiento
import org.json.JSONArray
import java.io.File
import java.util.UUID

class RepositorioEntrenamientos(private val context: Context) {
    private val fileName = "entrenamientos.json"

    fun listar(): List<Entrenamiento> {
        val file = File(context.filesDir, fileName)
        if (!file.exists()) return emptyList()
        val text = file.readText()
        if (text.isBlank()) return emptyList()
        val arr = JSONArray(text)
        val lista = mutableListOf<Entrenamiento>()
        for (i in 0 until arr.length()) {
            val obj = arr.getJSONObject(i)
            lista.add(Entrenamiento.fromJson(obj))
        }
        return lista
    }

    fun agregar(ent: Entrenamiento) {
        val lista = listar().toMutableList()
        lista.add(ent)
        guardarLista(lista)
    }

    private fun guardarLista(lista: List<Entrenamiento>) {
        val arr = JSONArray()
        lista.forEach { arr.put(it.toJson()) }
        val file = File(context.filesDir, fileName)
        file.writeText(arr.toString())
    }

    fun crearNuevo(
        fecha: String,
        tipo: String,
        nombre: String,
        duracionMin: Int,
        notas: String
    ): Entrenamiento {
        return Entrenamiento(
            id = UUID.randomUUID().toString(),
            fecha = fecha,
            tipo = tipo,
            nombre = nombre,
            duracionMin = duracionMin,
            notas = notas
        )
    }
}

