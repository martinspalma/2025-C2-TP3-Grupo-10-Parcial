package com.ort.parcial.c2.tp3.grupo10.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun SocialAuthButton(
    @DrawableRes iconId: Int,
    contentDesc: String,
    onClick: () -> Unit,
    size: Dp,
) {

    Box(
        modifier = Modifier
            .size(size)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = contentDesc,
            modifier = Modifier.size(size)
        )
    }
}