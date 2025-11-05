package com.ort.parcial.c2.tp3.grupo10.ui.utils

import com.ort.parcial.c2.tp3.grupo10.R

/**
 * Utilidad para obtener el icono de una categoría o savings
 */
fun getCategoryIcon(categoryName: String): Int {
    return when (categoryName.lowercase()) {
        "food" -> R.drawable.svg_food
        "transport" -> R.drawable.svg_transport
        "medicine" -> R.drawable.svg_medicine
        "groceries" -> R.drawable.svg_groceries
        "rent" -> R.drawable.svg_rent
        "gifts" -> R.drawable.svg_gift
        "savings" -> R.drawable.svg_savings
        "entertainment" -> R.drawable.svg_entertainment
        "travel" -> R.drawable.svg_avion
        "new house" -> R.drawable.svg_casallave
        "car" -> R.drawable.svg_auto
        "wedding" -> R.drawable.svg_anillos
        else -> R.drawable.svg_more
    }
}

