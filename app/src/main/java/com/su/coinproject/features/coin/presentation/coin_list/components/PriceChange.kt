package com.su.coinproject.features.coin.presentation.coin_list.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.su.coinproject.R
import com.su.coinproject.features.coin.presentation.coin_list.model.DisplayableNumber
import com.su.coinproject.ui.theme.CoinProjectTheme
import com.su.coinproject.ui.theme.greenColor
import com.su.coinproject.ui.theme.redColor

@Composable
fun PriceChange(
    change: DisplayableNumber,
    modifier: Modifier = Modifier
) {
    val contentColor = if (change.value < 0.0) {
        redColor
    } else {
        greenColor
    }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector =
                if (change.value < 0.0) {
                    Icons.Filled.ArrowDownward
                } else {
                    Icons.Filled.ArrowUpward
                }
            ,
            contentDescription = null,
            modifier = Modifier.size(12.dp),
            tint = contentColor
        )
        Text(
            text = "${change.value}",
            color = contentColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
private fun PriceChangePreview() {
    CoinProjectTheme {
        PriceChange(
            change = DisplayableNumber(
                value = 2.43,
                formatted = "2.43"
            )
        )
    }
}