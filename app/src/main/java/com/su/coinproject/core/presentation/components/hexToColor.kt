package com.su.coinproject.core.presentation.components

import androidx.compose.ui.graphics.Color

fun hexToColor(hex: String): Color {
    return Color(android.graphics.Color.parseColor(hex))
}