package com.ort.parcial.c2.tp3.grupo10.ui.screens.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Icon  // Agregar este import
import androidx.compose.ui.graphics.vector.ImageVector  // Agregar este import
import androidx.compose.ui.res.vectorResource  // Agregar este import
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.ui.components.BottomNavBar
import com.ort.parcial.c2.tp3.grupo10.ui.theme.BackgroundGreenWhiteAndLetters
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Caribbean
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LightGreen
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LightBlue
import com.ort.parcial.c2.tp3.grupo10.ui.theme.OceanBlueButton
import com.ort.parcial.c2.tp3.grupo10.ui.theme.OceanBlue
import com.ort.parcial.c2.tp3.grupo10.ui.theme.PoppinsFamily
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Void
import com.ort.parcial.c2.tp3.grupo10.ui.theme.MainGreen
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LettersAndIcons


data class CategoryItem(
    val name: String,
    val iconRes: Int
)

val categories = listOf(
    CategoryItem("Food", R.drawable.svg_food),
    CategoryItem("Transport", R.drawable.svg_transport),
    CategoryItem("Medicine", R.drawable.svg_medicine),
    CategoryItem("Groceries", R.drawable.svg_groceries),
    CategoryItem("Rent", R.drawable.svg_rent),
    CategoryItem("Gifts", R.drawable.svg_gift),
    CategoryItem("Savings", R.drawable.svg_savings),
    CategoryItem("Entertainment", R.drawable.svg_entertainment),
    CategoryItem("More", R.drawable.svg_more)
)

@Composable
fun CategoriesScreen(
    navController: NavHostController? = null,
    bottomSelected: Int = 3,
    onBottomSelect: (Int) -> Unit = {}
) {
    
    Scaffold(
        bottomBar = {
            BottomNavBar(
                selected = bottomSelected,
                onSelect = onBottomSelect
            )
        },
        containerColor = Color.Transparent  // Hacer transparente el container del Scaffold
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainGreen)  // Este fondo verde solo arriba
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Header Section (Green/Teal Background)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MainGreen)
                        .padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Back Arrow
                        Image(
                            painter = painterResource(id = R.drawable.ic_flecha_atras),
                            contentDescription = "Back",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { navController?.popBackStack() }
                        )
                        
                        // Title
                        Text(
                            text = "Categories",
                            color = LettersAndIcons,  // Cambiar de Color.White a LettersAndIcons
                            fontSize = 24.sp,
                            fontFamily = PoppinsFamily,
                            fontWeight = FontWeight.SemiBold,  // Cambiar de Bold a SemiBold
                            textAlign = TextAlign.Center
                        )
                        
                        // Notification Bell
                        Image(
                            painter = painterResource(id = R.drawable.ic_notification),
                            contentDescription = "Notifications",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { /* Handle notification click */ }
                        )
                    }
                }

                // Summary Section (Same Green Background)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MainGreen)
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                ) {
                    Column {
                        // Total Balance and Expense Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            // Total Balance
                            Column {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_income),
                                        contentDescription = "Income",
                                        modifier = Modifier.size(18.dp)  // Cambiado a 18.dp para igualar
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Total Balance",
                                        color = LettersAndIcons,
                                        fontSize = 14.sp,
                                        fontFamily = PoppinsFamily,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "$7,783.00",
                                    color = Color.White,
                                    fontSize = 24.sp,
                                    fontFamily = PoppinsFamily,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            
                            // Vertical Separator - Barra blanca
                            Box(
                                modifier = Modifier
                                    .width(2.dp)  // Un poco más ancha para que sea más visible
                                    .height(60.dp)  // Ajustar altura según necesites
                                    .background(Color.White)  // Blanco sólido en lugar de semitransparente
                            )
                            
                            // Total Expense
                            Column {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {     
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_expense),
                                        contentDescription = "Expense",
                                        modifier = Modifier.size(14.dp)  // Cambiar de 18.dp a 14.dp (o el tamaño que prefieras)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Total Expense",
                                        color = LettersAndIcons,
                                        fontSize = 14.sp,
                                        fontFamily = PoppinsFamily,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "-$1,187.40",
                                    color = OceanBlue,
                                    fontSize = 24.sp,
                                    fontFamily = PoppinsFamily,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        // Progress Bar Section
                        Column {
                            // Barra de progreso personalizada como en la imagen
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(32.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color.White)  // Fondo blanco para toda la barra
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    // Sección izquierda (30% - oscura)
                                    Box(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .weight(0.30f)
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 16.dp,
                                                    bottomStart = 16.dp,
                                                    topEnd = 0.dp,
                                                    bottomEnd = 0.dp
                                                )
                                            )
                                            .background(LettersAndIcons),  // Fondo oscuro
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Text(
                                            text = "30%",
                                            color = Color.White,
                                            fontSize = 14.sp,
                                            fontFamily = PoppinsFamily,
                                            fontWeight = FontWeight.Medium,
                                            modifier = Modifier.padding(start = 12.dp)
                                        )
                                    }
                                    
                                    // Sección derecha (70% - clara)
                                    Box(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .weight(0.70f)
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 0.dp,
                                                    bottomStart = 0.dp,
                                                    topEnd = 16.dp,
                                                    bottomEnd = 16.dp
                                                )
                                            )
                                            .background(Color.Transparent),  // Transparente ya que el fondo es blanco
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Text(
                                            text = "$20,000.00",
                                            color = LettersAndIcons,
                                            fontSize = 14.sp,
                                            fontFamily = PoppinsFamily,
                                            fontWeight = FontWeight.Medium,
                                            modifier = Modifier.padding(end = 12.dp)
                                        )
                                    }
                                }
                            }
                            
                            Spacer(modifier = Modifier.height(12.dp))
                            
                            // Descriptive Text con icono check
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.svg_check),
                                    contentDescription = "Check",
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "30% of your expenses, looks good.",
                                    color = LettersAndIcons,
                                    fontSize = 12.sp,
                                    fontFamily = PoppinsFamily,
                                    fontWeight = FontWeight.Normal,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

                // Main Content Area (Light Green/Off-White Background) - Llega hasta abajo
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                topStart = 40.dp,  // Aumentado de 32.dp a 40.dp para bordes más redondos
                                topEnd = 40.dp     // Aumentado de 32.dp a 40.dp para bordes más redondos
                            )
                        )
                        .background(LightGreen)
                        .padding(24.dp)
                        .padding(bottom = paddingValues.calculateBottomPadding()),
                ) {
                    // Category Grid (3x3) - scrollable
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(24.dp)  // Aumentado de 16.dp a 24.dp
                    ) {
                        // Row 1
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            CategoryButton(
                                category = categories[0],
                                onClick = { 
                                    // Navegar a la pantalla de detalle de categoría (cuando exista)
                                    // navController?.navigate("category_detail/${categories[0].name}")
                                }
                            )
                            CategoryButton(
                                category = categories[1],
                                onClick = { 
                                    // navController?.navigate("category_detail/${categories[1].name}")
                                }
                            )
                            CategoryButton(
                                category = categories[2],
                                onClick = { 
                                    // navController?.navigate("category_detail/${categories[2].name}")
                                }
                            )
                        }
                        
                        // Row 2
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            CategoryButton(
                                category = categories[3],
                                onClick = { 
                                    // navController?.navigate("category_detail/${categories[3].name}")
                                }
                            )
                            CategoryButton(
                                category = categories[4],
                                onClick = { 
                                    // navController?.navigate("category_detail/${categories[4].name}")
                                }
                            )
                            CategoryButton(
                                category = categories[5],
                                onClick = { 
                                    // navController?.navigate("category_detail/${categories[5].name}")
                                }
                            )
                        }
                        
                        // Row 3
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            CategoryButton(
                                category = categories[6],
                                onClick = { 
                                    // navController?.navigate("category_detail/${categories[6].name}")
                                }
                            )
                            CategoryButton(
                                category = categories[7],
                                onClick = { 
                                    // navController?.navigate("category_detail/${categories[7].name}")
                                }
                            )
                            CategoryButton(
                                category = categories[8],
                                onClick = { 
                                    // navController?.navigate("category_detail/${categories[8].name}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.CategoryButton(
    category: CategoryItem,
    onClick: () -> Unit = {}
) {
    // Estado para indicar que se hizo click (feedback visual)
    var isClicked by remember { mutableStateOf(false) }
    
    // Resetear el estado después de un tiempo corto
    LaunchedEffect(isClicked) {
        if (isClicked) {
            delay(200) // 200ms de feedback visual
            isClicked = false
        }
    }
    
    // Column principal que contiene el cuadrado y el texto fuera
    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
      
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable(
                    onClick = {
                        isClicked = true  // Activar feedback visual
                        onClick()  // Ejecutar la acción
                    }
                )
                .background(
                    // OceanBlueButton cuando se hizo click (feedback temporal), LightBlue por defecto
                    color = if (isClicked) OceanBlueButton else LightBlue
                ),
            contentAlignment = Alignment.Center
        ) {
            // Icono más PEQUEÑO
            Icon(
                imageVector = ImageVector.vectorResource(id = category.iconRes),
                contentDescription = category.name,
                modifier = Modifier.size(50.dp),
                tint = Color.White
            )
        }
        
        // Texto FUERA del cuadrado, debajo
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = category.name,
            color = Void,
            fontSize = 14.sp,  // Aumentado de 12.sp a 14.sp
            fontFamily = PoppinsFamily,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}