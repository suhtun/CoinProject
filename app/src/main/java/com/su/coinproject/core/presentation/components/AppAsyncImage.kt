package com.su.coinproject.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.su.coinproject.ui.theme.CoinProjectTheme


@Composable
fun AppAsyncImage(
    modifier: Modifier = Modifier.size(60.dp),
    url: String,
    name: String,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
) {

    // Create an ImageRequest with the SVG decoder
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    // Create a Box with gradient background and rounded corners
    Box(
        modifier = Modifier
            .border(
                width = 2.dp,
               color =  MaterialTheme.colorScheme.primary,
                shape = CircleShape,
            )
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(url)
                    .decoderFactory(SvgDecoder.Factory()) // Use SVG decoder here
                    .build(),
                imageLoader = imageLoader
            ),
            contentDescription = null,
            modifier = modifier
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

    }
}

@Preview
@Composable
fun AppAsyncImagePreview(modifier: Modifier = Modifier) {
    CoinProjectTheme {
        AppAsyncImage(url = "", name = "")
    }
}