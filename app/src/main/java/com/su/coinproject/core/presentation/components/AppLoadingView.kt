package com.su.coinproject.core.presentation.components

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.su.coinproject.ui.theme.blueColor

@Composable
fun AppLoadingView(modifier: Modifier = Modifier) {
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            color = blueColor,
            modifier = Modifier.size(40.dp)
        )
    }
}