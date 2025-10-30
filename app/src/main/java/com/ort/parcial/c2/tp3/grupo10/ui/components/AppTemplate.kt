package com.ort.parcial.c2.tp3.grupo10.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTemplate(
    title: String,
    onBack: () -> Unit,
    onBellClick: () -> Unit,
    bottomSelected: Int,
    onBottomSelect: (Int) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    // Aquí puedes poner un botón de atrás si onBack no es nulo
                },
                actions = {
                    IconButton(onClick = onBellClick) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notificaciones")
                    }
                }
            )
        },
        // Aquí es donde se añade la barra de navegación
        bottomBar = {
            BottomNavBar(
                selected = bottomSelected,
                onSelect = onBottomSelect
            )
        }
    ) { innerPadding ->
        // El contenido de tu pantalla se pasa aquí
        content(innerPadding)
    }
}