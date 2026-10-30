package com.example.kotlinpetmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.kotlinpetmanager.ui.theme.KotlinPetManagerTheme
import com.example.kotlinpetmanager.view.PacienteView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            KotlinPetManagerTheme(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = false
            ) {
                PacienteView()
            }
        }
    }
}