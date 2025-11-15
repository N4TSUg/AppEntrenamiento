package com.example.appentrenamiento.Entities

import org.json.JSONObject

data class Entrenamiento(
    val id: String,
    val fecha: String,
    val tipo: String,
    val nombre: String,
    val duracionMin: Int,
    val notas: String
) {
    fun toJson(): JSONObject {
        val obj = JSONObject()
        obj.put("id", id)
        obj.put("fecha", fecha)
        obj.put("tipo", tipo)
        obj.put("nombre", nombre)
        obj.put("duracionMin", duracionMin)
        obj.put("notas", notas)
        return obj
    }

    companion object {
        fun fromJson(obj: JSONObject): Entrenamiento {
            return Entrenamiento(
                id = obj.optString("id"),
                fecha = obj.optString("fecha"),
                tipo = obj.optString("tipo"),
                nombre = obj.optString("nombre"),
                duracionMin = obj.optInt("duracionMin"),
                notas = obj.optString("notas")
            )
        }
    }
}
