package com.ort.parcial.c2.tp3.grupo10.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ort.parcial.c2.tp3.grupo10.R
import com.ort.parcial.c2.tp3.grupo10.domain.model.TransactionType
import com.ort.parcial.c2.tp3.grupo10.ui.theme.BackgroundGreenWhiteAndLetters
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Caribbean
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Honeydew
import com.ort.parcial.c2.tp3.grupo10.ui.theme.Honeydew2
import com.ort.parcial.c2.tp3.grupo10.ui.theme.LettersAndIcons
import com.ort.parcial.c2.tp3.grupo10.ui.theme.MainGreen
import com.ort.parcial.c2.tp3.grupo10.ui.theme.OceanBlue
import com.ort.parcial.c2.tp3.grupo10.ui.theme.PoppinsFamily
import com.ort.parcial.c2.tp3.grupo10.ui.theme.vividBlue

@Suppress("UNUSED_PARAMETER")
@Composable
fun HomeScreen(navController: NavController? = null) {
    val scrollState = rememberScrollState()
    var selectedRange by remember { mutableStateOf(TimeRange.WEEKLY) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGreenWhiteAndLetters)
            .verticalScroll(scrollState)
    ) {
        HeaderSection()

        CombinedStatsCard(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        TimeRangeSelector(
            selected = selectedRange,
            onSelect = { selectedRange = it },
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))
        

        RecentTransactions(
            transactions = sampleTransactions,
            modifier = Modifier.padding(bottom = 32.dp)
        )
    }
}

@Composable
private fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 36.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(vividBlue, MainGreen)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 28.dp)
        ) {
            Text(
                text = "Hi, Welcome Back",
                color = Color.White,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 26.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Good Morning",
                color = Color.White.copy(alpha = 0.85f),
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BalanceCard(
                    title = "Total Balance",
                    amount = "$7,783.00",
                    iconRes = R.drawable.ic_income,
                    amountColor = Color.White,
                    modifier = Modifier.weight(1f)
                )
                BalanceCard(
                    title = "Total Expense",
                    amount = "-$1,187.40",
                    iconRes = R.drawable.ic_expense,
                    amountColor = OceanBlue,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            ProgressSection(
                progress = 0.30f,
                goalText = "$20,000.00 goal",
                description = "30% Of Your Expenses, Looks Good."
            )
        }
    }
}

@Composable
private fun BalanceCard(
    title: String,
    amount: String,
    iconRes: Int,
    amountColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White.copy(alpha = 0.12f))
            .padding(horizontal = 18.dp, vertical = 20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier.size(36.dp),
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.18f)
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = title,
                color = Color.White,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
    
        Text(
            text = amount,
            color = amountColor,
            fontFamily = PoppinsFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
    }
}

@Composable
private fun ProgressSection(
    progress: Float,
    goalText: String,
    description: String
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Progress",
                color = Color.White,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
            Text(
                text = goalText,
                color = Color.White.copy(alpha = 0.8f),
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(18.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White.copy(alpha = 0.2f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
            )
            Text(
                text = "${(progress * 100).toInt()}%",
                color = LettersAndIcons,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 12.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.svg_check),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = description,
                color = Color.White,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun CombinedStatsCard(modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(28.dp)
    Column(
        modifier = modifier
            .shadow(elevation = 12.dp, shape = shape, clip = false)
            .clip(shape)
            .background(Color.White)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape,
                    color = Honeydew
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.svg_savings),
                        contentDescription = null,
                        modifier = Modifier.padding(20.dp)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Savings on Goals",
                    color = LettersAndIcons,
                    fontFamily = PoppinsFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "$4,000.00",
                    color = MainGreen,
                    fontFamily = PoppinsFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                StatRow(
                    title = "Revenue Last Week",
                    amount = "$4,000.00",
                    iconRes = R.drawable.ic_grafico,
                    iconTint = Caribbean
                )
                Spacer(modifier = Modifier.height(12.dp))
                StatRow(
                    title = "Food Last Week",
                    amount = "-$1,200.00",
                    iconRes = R.drawable.svg_food,
                    iconTint = OceanBlue
                )
            }
        }
    }
}

@Composable
private fun StatRow(
    title: String,
    amount: String,
    iconRes: Int,
    iconTint: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(BackgroundGreenWhiteAndLetters),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier
                .padding(12.dp)
                .size(48.dp),
            shape = CircleShape,
            color = Color.White
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.padding(12.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        ) {
            Text(
                text = title,
                color = LettersAndIcons,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
            Text(
                text = amount,
                color = iconTint,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun TimeRangeSelector(
    selected: TimeRange,
    onSelect: (TimeRange) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Statistics",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = PoppinsFamily,
            fontWeight = FontWeight.SemiBold,
            color = LettersAndIcons
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            TimeRange.values().forEach { range ->
                val isSelected = range == selected
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (isSelected) MainGreen else Color.White)
                        .clickable { onSelect(range) }
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = range.label,
                        fontFamily = PoppinsFamily,
                        fontWeight = FontWeight.Medium,
                        color = if (isSelected) Color.White else LettersAndIcons
                    )
                }
            }
        }
    }
}

@Composable
private fun RecentTransactions(
    transactions: List<HomeTransaction>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Recent Transactions",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = PoppinsFamily,
            fontWeight = FontWeight.SemiBold,
            color = LettersAndIcons,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            transactions.forEach { tx ->
                TransactionRow(tx)
            }
        }
    }
}

@Composable
private fun TransactionRow(tx: HomeTransaction) {
    val shape = RoundedCornerShape(24.dp)
    val amountColor = if (tx.type == TransactionType.INCOME) MainGreen else OceanBlue
    val sign = if (tx.type == TransactionType.INCOME) "+" else "-"
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 8.dp, shape = shape, clip = false)
            .clip(shape)
            .background(Color.White)
            .padding(horizontal = 18.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(54.dp),
            shape = CircleShape,
            color = BackgroundGreenWhiteAndLetters
        ) {
            Image(
                painter = painterResource(id = tx.iconRes),
                contentDescription = null,
                modifier = Modifier.padding(14.dp)
            )
        }
        Spacer(modifier = Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = tx.title,
                color = LettersAndIcons,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = tx.subtitle,
                color = Honeydew2,
                fontFamily = PoppinsFamily,
                fontSize = 12.sp
            )
        }
        Text(
            text = "$sign$${String.format("%.2f", tx.amount)}",
            color = amountColor,
            fontFamily = PoppinsFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
    }
}

private data class HomeTransaction(
    val title: String,
    val subtitle: String,
    val amount: Double,
    val type: TransactionType,
    val iconRes: Int
)

enum class TimeRange(val label: String) {
    DAILY("Daily"),
    WEEKLY("Weekly"),
    MONTHLY("Monthly")
}

private val sampleTransactions = listOf(
    HomeTransaction(
        title = "Salary",
        subtitle = "18:27 - April 30",
        amount = 4000.0,
        type = TransactionType.INCOME,
        iconRes = R.drawable.ic_income
    ),
    HomeTransaction(
        title = "Groceries",
        subtitle = "17:00 - April 24",
        amount = 100.0,
        type = TransactionType.EXPENSE,
        iconRes = R.drawable.svg_groceries
    ),
    HomeTransaction(
        title = "Rent",
        subtitle = "8:30 - April 15",
        amount = 674.40,
        type = TransactionType.EXPENSE,
        iconRes = R.drawable.svg_rent
    )
)