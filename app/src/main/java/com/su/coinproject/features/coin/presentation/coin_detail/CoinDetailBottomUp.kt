package com.su.coinproject.features.coin.presentation.coin_detail

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.su.coinproject.core.presentation.components.AppAsyncImage
import com.su.coinproject.core.presentation.components.hexToColor
import com.su.coinproject.features.coin.presentation.coin_list.CoinListAction
import com.su.coinproject.features.coin.presentation.coin_list.CoinListViewModel
import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi
import com.su.coinproject.ui.theme.blueColor
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun CoinDetailBottomUp(
    modifier: Modifier = Modifier,
    coinUi: CoinUi? = null,
    onDismissed: () -> Unit = {},
) {

    coinUi ?: return

    // Create a scope to launch coroutines
    val scope = rememberCoroutineScope()

    // State for the Bottom Sheet dialog visibility
    val sheetState = rememberModalBottomSheetState()

    val primaryFontColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }

    val secondaryFontColor = if (isSystemInDarkTheme()) {
        Color.LightGray
    } else {
        Color.DarkGray
    }

    ModalBottomSheet(sheetState = sheetState,
        onDismissRequest = {
            onDismissed()
        }
    ) {
        // Content inside the Bottom Sheet
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AppAsyncImage(
                    modifier = Modifier.size(50.dp),
                    url = coinUi.iconUrl,
                    name = coinUi.name
                )
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    val styledText = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold, fontSize = 18.sp,
                                color = coinUi.color?.let { hexToColor(it) } ?: primaryFontColor
                            )
                        ) {
                            append("${coinUi.name} ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp, color = primaryFontColor
                            )
                        ) {
                            append("(${coinUi.symbol})")
                        }
                    }
                    Text(
                        text = styledText,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Row {
                        Text(
                            text = "PRICE",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = primaryFontColor
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "$ ${coinUi.price.formatted}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = secondaryFontColor
                        )
                    }
                    Row {
                        Text(
                            text = "MARKET CAP",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = primaryFontColor
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "$ ${coinUi.marketCap}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = secondaryFontColor
                        )
                    }
                }
            }

//            Text(
//                text = coinUi.description ?: "No description",
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Normal,
//                color = secondaryFontColor,
//                modifier = Modifier.padding(16.dp)
//            )
//
//            Spacer(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(1.dp)
//                    .background(Color.LightGray)
//            )
//
//            coinUi.websiteUrl?.let { websiteUrl ->
//                Text(text = "GO TO WEBSITE",
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = blueColor,
//                    modifier = Modifier
//                        .padding(vertical = 16.dp)
//                        .align(Alignment.CenterHorizontally)
//                        .clickable {
//                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl))
//                            context.startActivity(intent)
//                        })
//            }

        }
    }

    LaunchedEffect(sheetState) {
        scope.launch {
            sheetState.show()
        }
    }
}
