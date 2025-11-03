package com.ort.parcial.c2.tp3.grupo10.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.components.AppScreenShell
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LettersAndIcons
import com.ort.parcial.c2.tp3.grupo10.ui.theme.PoppinsFamily
import com.ort.parcial.c2.tp3.grupo10.ui.components.BottomNavBar
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import com.ort.parcial.c2.tp3.grupo10.ui.theme.DarkModeGreenBar
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.ort.parcial.c2.tp3.grupo10.ui.components.ProfileOptionItem


@Composable
fun ProfileScreen(navController: NavHostController) {

    val PROFILE_HEADER_HEIGHT = 120.dp + 60.dp // Aprox. 180dp para el corte de la foto.
    var selectedIndex by remember { mutableIntStateOf(4) }
    val imageUrl = "https://picsum.photos/200/200"
    val floatingProfileContent: @Composable () -> Unit = {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // --- A. IMAGEN DE PERFIL (COIL) ---
            AsyncImage(
                model = imageUrl,
                contentDescription = "Foto de perfil de John Smith",
                contentScale = ContentScale.Crop,

                modifier = Modifier
                    .size(120.dp) // Tamaño del círculo
                    .clip(CircleShape)
                    .background(Color.White),
                error = painterResource(id = R.drawable.ic_perfil)
            )

            Spacer(Modifier.height(8.dp))

            // --- B. NOMBRE: John Smith ---
            Text(
                text = "John Smith",
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                lineHeight = 20.sp,
                color = DarkModeGreenBar
            )

            // --- C. ID: 25030024 ---
            Row {
                Text(
                    text = "ID: ",
                    color = LettersAndIcons,
                    fontFamily = PoppinsFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    lineHeight = 13.sp,
                )
                Text(
                    text = "25030024",
                    color = LettersAndIcons.copy(alpha = 0.8f),
                    fontFamily = PoppinsFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 13.sp,
                    lineHeight = 13.sp,
                )
            }
        }
    }

    AppScreenShell(
        screenTitle = stringResource(R.string.profile_screen_title),
        headerHeight = PROFILE_HEADER_HEIGHT, // Usamos 180dp (o el valor ajustado)
        navController = navController,
        bottomBar = {
            BottomNavBar(
                selected = selectedIndex,
                onSelect = { newIndex -> selectedIndex = newIndex }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding) // Aplicamos el padding de la BottomBar
                .padding(horizontal = 32.dp), // Padding horizontal para el contenido
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Spacer(Modifier.height(160.dp)) // Altura del círculo (120dp) + margen inferior.

//LISTA DE OPCIONES DE PERFIL ---
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // EDIT PROFILE (57dp de ícono, asumimos altura de 64dp para el item)
                ProfileOptionItem(
                    label = stringResource(R.string.profile_option_edit_profile),
                    iconRes = R.drawable.ic_edit_profile,
                    onClick = { navController.navigate("edit_profile") },
                    itemHeight = 70.dp
                )


                // SECURITY
                ProfileOptionItem(
                    label = stringResource(R.string.profile_option_security),
                    iconRes = R.drawable.ic_security,
                    onClick = { navController.navigate("security") },
                    itemHeight = 70.dp
                )


                // SETTING (57dp de ícono, asumimos altura de 64dp)
                ProfileOptionItem(
                    label = stringResource(R.string.profile_option_setting),
                    iconRes = R.drawable.ic_setting,
                    onClick = { /* Navegación a Ajustes */ },
                    itemHeight = 70.dp
                )


                // HELP (57dp de ícono, asumimos altura de 64dp)
                ProfileOptionItem(
                    label = stringResource(R.string.profile_option_help),
                    iconRes = R.drawable.ic_help,
                    onClick = { /* Navegación a Ayuda */ },
                    itemHeight = 70.dp
                )


                // LOGOUT (57dp de ícono, asumimos altura de 64dp)
                ProfileOptionItem(
                    label = stringResource(R.string.profile_option_logout),
                    iconRes = R.drawable.ic_logout,
                    onClick = { /* Lógica de Logout */ },
                    itemHeight = 70.dp
                )

            }

            Spacer(Modifier.weight(1f))
        }
    }

    // 3. LLAMAMOS AL CONTENIDO FLOTANTE PARA QUE SE DIBUJE SOBRE EL SHELL
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = PROFILE_HEADER_HEIGHT - 60.dp),
                contentAlignment = Alignment.TopCenter
    ) {
        floatingProfileContent()
    }
}