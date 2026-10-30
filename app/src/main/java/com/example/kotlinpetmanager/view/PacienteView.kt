package com.example.kotlinpetmanager.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinpetmanager.model.Paciente
import com.example.kotlinpetmanager.viewmodel.PacienteViewModel
import androidx.compose.foundation.shape.RoundedCornerShape


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PacienteView(
    viewModel: PacienteViewModel = viewModel()
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("KotlinPet Manager 🐾")
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .imePadding()
        ) {

            // Titulo del formulario

            Text(
                text = "Registro de pacientes",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Campo nombre

            OutlinedTextField(
                value = viewModel.nombre,
                onValueChange = {
                    viewModel.actualizarNombre(it)
                },
                label = {
                    Text("Nombre del paciente")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo especie

            OutlinedTextField(
                value = viewModel.especie,
                onValueChange = {
                    viewModel.actualizarEspecie(it)
                },
                label = {
                    Text("Especie o raza")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo edad

            OutlinedTextField(
                value = viewModel.edad,
                onValueChange = {
                    viewModel.actualizarEdad(it)
                },
                label = {
                    Text("Edad en años")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo contacto de emergencia

            OutlinedTextField(
                value = viewModel.contactoEmergencia,
                onValueChange = {
                    viewModel.actualizarContacto(it)
                },
                label = {
                    Text("Contacto de emergencia")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Casilla de urgencia

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = viewModel.esUrgencia,
                    onCheckedChange = {
                        viewModel.actualizarUrgencia(it)
                    }
                )

                Text("¿Requiere atención de urgencia?")
            }

            // Mensaje de error

            if (viewModel.mensajeError.isNotEmpty()) {

                Text(
                    text = viewModel.mensajeError,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )

            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botones

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                OutlinedButton(
                    onClick = {
                        viewModel.limpiarFormulario()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Limpiar")
                }

                Button(
                    onClick = {
                        viewModel.agregarPaciente()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Registrar")
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            // Titulo de la lista

            Text(
                text = "Pacientes registrados: ${viewModel.pacientes.size}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Lista dinamica

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(
                    items = viewModel.pacientes,
                    key = { paciente -> paciente.id }
                ) { paciente ->

                    PacienteCard(paciente = paciente)

                }

            }

        }

    }

}


// Componente para mostrar cada paciente

@Composable
fun PacienteCard(
    paciente: Paciente
) {

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 3.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = paciente.nombre,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text("Especie / Raza: ${paciente.especie}")

            Text("Edad: ${paciente.edad} años")

            Text("Contacto: ${paciente.contactoEmergencia}")

            Spacer(modifier = Modifier.height(8.dp))

            if (paciente.esUrgencia) {

                Text(
                    text = "⚠ ATENCION DE URGENCIA",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelLarge
                )

            } else {

                Text(
                    text = "Atencion normal",
                    color = MaterialTheme.colorScheme.primary
                )

            }

        }

    }

}
