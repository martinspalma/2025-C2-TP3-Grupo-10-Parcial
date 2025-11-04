package com.ort.parcial.c2.tp3.grupo10.ui.components

import android.content.Context
import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ort.parcial.c2.tp3.grupo10.MainActivity2
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Caribbean
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LightGreen
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Void

data class BottomNavItem(
    val label: String,
    @DrawableRes val iconRes: Int
)

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
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = LightGreen,
                shape = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp)
            )
            .height(90.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            bottomNavItems.forEachIndexed { index, item ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                onSelect(index)
                                if (index == 4) {
                                    val intent = Intent(context, MainActivity2::class.java)
                                    context.startActivity(intent)
                                }
                            }
                        )
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        if (selected == index) {
                            Box(
                                modifier = Modifier
                                    .size(size = 50.dp)
                                    .background(
                                        color = Caribbean,
                                        shape = RoundedCornerShape(20.dp)
                                    )
                            )
                        }
                        // ---- LA NUEVA SOLUCIÓN ----
                        Image(
                            painter = painterResource(id = item.iconRes),
                            contentDescription = item.label,
                            colorFilter = ColorFilter.tint(Void)
                        )
                        // -------------------------
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewBottomNavBar() {
    var selectedIndex by remember { mutableIntStateOf(0) }

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