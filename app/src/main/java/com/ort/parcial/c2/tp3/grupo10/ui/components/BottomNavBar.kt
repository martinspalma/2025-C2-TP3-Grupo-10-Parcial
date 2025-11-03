package com.ort.parcial.c2.tp3.grupo10.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Caribbean
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LightGreen
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Void

// 1. Se define una data class para que el código sea más claro y robusto
data class BottomNavItem(
    val label: String,
    @DrawableRes val iconRes: Int
)

// 2. Se usa la data class para definir los ítems de navegación
val bottomNavItems = listOf(
    BottomNavItem("Home", R.drawable.ic_home),
    BottomNavItem("Analysis", R.drawable.ic_analysis),
    BottomNavItem("Transactions", R.drawable.ic_transactions),
    BottomNavItem("Category", R.drawable.ic_category),
    BottomNavItem("Profile", R.drawable.ic_perfil)
)

@Composable
fun BottomNavBar(
    selected: Int,
    onSelect: (Int) -> Unit,
    cornerRadius: Dp = 34.dp

) {
    NavigationBar(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp)),
        containerColor = LightGreen
    ) {
        bottomNavItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selected == index,
                onClick = { onSelect(index) },
                icon = {
                    Box(contentAlignment = Alignment.Center) {
                        // (NUEVO) Dibujamos nuestro propio indicador si está seleccionado
                        if (selected == index) {
                            Box(
                                modifier = Modifier
                                    .size(size = 50.dp)
                                    .background(
                                        color = Caribbean,
                                        // Un rectangulo con puntas redondeadas
                                        shape = RoundedCornerShape(20.dp)
                                    )
                            )
                        }
                        Icon(
                            imageVector = ImageVector.vectorResource(id = item.iconRes),
                            contentDescription = item.label
                        )
                    }
                },
                label = null,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Void,
                    unselectedIconColor = Void,
                    // (NUEVO) Hacemos el indicador por defecto transparente
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

// --- PREVIEW MEJORADA PARA MOSTRAR POSICIONAMIENTO ---
@Preview(showBackground = true)
@Composable
fun PreviewBottomNavBar() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    // Envuelve la preview en un Scaffold para demostrar que se queda abajo.
    Scaffold(
        bottomBar = {
            BottomNavBar(
                selected = selectedIndex,
                onSelect = { newIndex ->
                    selectedIndex = newIndex
                }
            )
        }
    ) { paddingValues ->
        // Contenido de ejemplo (incluso si está vacío, la barra se queda abajo)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Contenido de la pantalla")
        }
    }
}
