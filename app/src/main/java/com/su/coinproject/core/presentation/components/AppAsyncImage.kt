package com.su.coinproject.core.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.su.coinproject.R
import io.ktor.http.Url

@Composable
fun AppAsyncImage(
    modifier: Modifier = Modifier.size(40.dp),
    url: String,
    name: String
) {
    AsyncImage(
        model = url,
        contentDescription = name,
        placeholder = painterResource(id = R.drawable.baseline_image_not_supported_24),
        error = painterResource(id = R.drawable.baseline_image_not_supported_24),
        modifier = modifier
    )
}