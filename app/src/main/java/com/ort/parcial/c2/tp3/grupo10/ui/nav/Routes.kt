package com.ort.parcial.c2.tp3.grupo10.ui.nav

import androidx.annotation.DrawableRes
import com.ort.parcial.c2.tp3.grupo10.R

sealed class Route(val path: String) {
    data object Home : Route("home")
    data object Search : Route("search")
    data object Activity : Route("activity")
    data object Stacks : Route("stacks")
    data object Profile : Route("profile")
}

data class BottomItem(
    val route: Route,
    @DrawableRes val iconRes: Int
)

val bottomItems = listOf(
    BottomItem(Route.Home, R.drawable.ic_home),      // casa
    BottomItem(Route.Search, R.drawable.ic_analysis),  // lupa
    BottomItem(Route.Activity, R.drawable.ic_transactions), // actividad/estadísticas
    BottomItem(Route.Stacks, R.drawable.ic_category),  // stacks
    BottomItem(Route.Profile, R.drawable.ic_perfil) // perfil
)