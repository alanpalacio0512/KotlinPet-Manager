
package com.example.kotlinpetmanager.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kotlinpetmanager.model.Paciente
import java.util.UUID

class PacienteViewModel : ViewModel() {

    // Variables del formulario

    var nombre by mutableStateOf("")
        private set

    var especie by mutableStateOf("")
        private set

    var edad by mutableStateOf("")
        private set

    var contactoEmergencia by mutableStateOf("")
        private set

    var esUrgencia by mutableStateOf(false)
        private set

    // Lista de pacientes

    var pacientes by mutableStateOf<List<Paciente>>(emptyList())
        private set

    // Mensaje de validacion

    var mensajeError by mutableStateOf("")
        private set

    // Funciones para actualizar los campos

    fun actualizarNombre(valor: String) {
        nombre = valor
    }

    fun actualizarEspecie(valor: String) {
        especie = valor
    }

    fun actualizarEdad(valor: String) {
        edad = valor
    }

    fun actualizarContacto(valor: String) {
        contactoEmergencia = valor
    }

    fun actualizarUrgencia(valor: Boolean) {
        esUrgencia = valor
    }

    // Funcion para agregar pacientes

    fun agregarPaciente() {

        if (nombre.isBlank() ||
            especie.isBlank() ||
            edad.isBlank() ||
            contactoEmergencia.isBlank()
        ) {
            mensajeError = "Complete todos los campos"
            return
        }

        val edadIngresada = edad.toIntOrNull()

        if (edadIngresada == null || edadIngresada < 0) {
            mensajeError = "Ingrese una edad valida"
            return
        }

        val nuevoPaciente = Paciente(
            id = UUID.randomUUID().toString(),
            nombre = nombre.trim(),
            especie = especie.trim(),
            edad = edadIngresada,
            esUrgencia = esUrgencia,
            contactoEmergencia = contactoEmergencia.trim()
        )

        pacientes = pacientes + nuevoPaciente

        limpiarFormulario()
    }

    // Funcion para limpiar el formulario

    fun limpiarFormulario() {

        nombre = ""
        especie = ""
        edad = ""
        contactoEmergencia = ""
        esUrgencia = false
        mensajeError = ""

    }
}
