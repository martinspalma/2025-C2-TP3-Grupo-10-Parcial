package com.ort.parcial.c2.tp3.grupo10.ui.screens.notifications

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.layout.IntrinsicSize
import com.ort.parcial.c2.tp3.grupo10.domain.model.NotificationItem
import com.ort.parcial.c2.tp3.grupo10.ui.theme.*

@Composable
fun NotificationCard(item: NotificationItem, topPadding: Dp = 6.dp) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 16.dp)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.iconResId),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(38.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Columna de textos principales
            Column(modifier = Modifier.weight(2f)) {
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Void,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.message,
                    fontSize = 13.sp,
                    color = Void,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                if (item.category != null && item.amount != null) {
                    Text(
                        text = "${item.category} | ${item.amount}",
                        color = OceanBlue,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        // ===== Debajo de la fila: hora - fecha a la derecha =====
        val timeDate = buildString {
            if (item.time.isNotBlank()) append(item.time)
            if (item.time.isNotBlank() && item.date.isNotBlank()) append(" - ")
            if (item.date.isNotBlank()) append(item.date)
        }
        if (timeDate.isNotBlank()) {
            Text(
                text = timeDate,
                fontSize = 12.sp,
                color = vividBlue,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.End)   // >>> esto la manda a la derecha
            )
        }

        // Línea divisoria
        Divider(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .padding(start = 5.dp),   // ajustá si querés que arranque más a la izquierda
            thickness = 1.dp,
            color = Caribbean
        )
    }
}

