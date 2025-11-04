package com.ort.parcial.c2.tp3.grupo10.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.components.AppScreenShell
import com.ort.parcial.c2.tp3.grupo10.ui.components.BottomNavBar
import com.ort.parcial.c2.tp3.grupo10.ui.theme.*

@Composable
fun EditProfileScreen(navController: NavHostController) {

    val PROFILE_HEADER_HEIGHT = 120.dp + 60.dp
    var selectedIndex by remember { mutableIntStateOf(4) }
    val imageUrl = "https://picsum.photos/200/200"

    val floatingProfileContent: @Composable () -> Unit = {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            AsyncImage(
                model = imageUrl,
                contentDescription = "Foto de perfil de John Smith",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                error = painterResource(id = R.drawable.ic_perfil)
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "John Smith",
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = DarkModeGreenBar
            )

            Row {
                Text(
                    text = "ID: ",
                    color = LettersAndIcons,
                    fontFamily = PoppinsFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                )
                Text(
                    text = "25030024",
                    color = LettersAndIcons.copy(alpha = 0.8f),
                    fontFamily = PoppinsFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 13.sp,
                )
            }
        }
    }


    AppScreenShell(
        screenTitle = "Edit My Profile",
        headerHeight = PROFILE_HEADER_HEIGHT,
        navController = navController,
        bottomBar = {
            BottomNavBar(
                selected = selectedIndex,
                onSelect = { idx ->
                    selectedIndex = idx
                    when (idx) {
                        0 -> navController.navigate("home")
                        1 -> navController.navigate("analysis")
                        2 -> navController.navigate("transactions")
                        3 -> navController.navigate("categories")
                        4 -> navController.navigate("profile")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(140.dp))
            EditProfileContent()
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = PROFILE_HEADER_HEIGHT - 60.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        floatingProfileContent()
    }
}



@Composable
private fun EditProfileContent() {
    var username by remember { mutableStateOf("John Smith") }
    var phone by remember { mutableStateOf("+44 555 5555 55") }
    var email by remember { mutableStateOf("example@example.com") }
    var pushEnabled by remember { mutableStateOf(true) }
    var darkEnabled by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .background(BackgroundGreenWhiteAndLetters)
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = "Account Settings",
                color = Void,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )

            // --- ¡CAMBIO CLAVE AQUÍ! ---
            // Volvemos a usar el Composable LabeledReadOnlyField
            LabeledReadOnlyField(
                label = "Username",
                value = username
            )
            Spacer(Modifier.height(10.dp))

            LabeledReadOnlyField(
                label = "Phone",
                value = phone
            )
            Spacer(Modifier.height(10.dp))

            LabeledReadOnlyField(
                label = "Email Address",
                value = email
            )

            // --- El resto se mantiene igual ---
            Spacer(Modifier.height(16.dp))

            SettingsSwitchRow(
                title = "Push Notifications",
                checked = pushEnabled,
                onCheckedChange = { pushEnabled = it }
            )
            SettingsSwitchRow(
                title = "Turn Dark Theme",
                checked = darkEnabled,
                onCheckedChange = { darkEnabled = it }
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = { /* TODO: Aquí iría la lógica para, por ejemplo, cerrar sesión o ir a otra pantalla */ },
                shape = RoundedCornerShape(26.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Caribbean),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(48.dp)
            ) {
                Text(
                    text = "Update Profile",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}






@Composable
private fun LabeledReadOnlyField(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = Void,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 8.dp, bottom = 6.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(LightGreen.copy(alpha = 0.65f))
                .padding(horizontal = 14.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = value,
                color = LettersAndIcons.copy(alpha = 0.9f),
                fontSize = 14.sp
            )
        }
    }
}




@Composable
private fun SettingsSwitchRow(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = Void,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.border(
                width = 2.dp,
                color = Color.Transparent,
                shape = CircleShape
            ),
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Caribbean,


                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Caribbean.copy(alpha = 0.5f),
                uncheckedBorderColor = Color.Transparent
            )
        )

    }
}

/* ---------- PREVIEW ---------- */
@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen(navController = rememberNavController())
}
