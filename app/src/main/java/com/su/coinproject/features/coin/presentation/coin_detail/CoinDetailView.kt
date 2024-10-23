package com.su.coinproject.features.coin.presentation.coin_detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.su.coinproject.features.coin.domain.Coin
import com.su.coinproject.features.coin.domain.CoinDetail
import com.su.coinproject.features.coin.presentation.coin_list.model.CoinUi
import com.su.coinproject.features.coin.presentation.coin_list.model.toCoinUi
import com.su.coinproject.ui.theme.blueColor
import com.su.coinproject.ui.theme.greenColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinDetailView(
    coinUi: CoinUi,
    coinDetail: CoinDetail,
    showDialog: Boolean,
    updateDialogDisplayStatus: (Boolean) -> Unit
) {
    val context = LocalContext.current

    // Create a scope to launch coroutines
    val scope = rememberCoroutineScope()

    // State for the Bottom Sheet dialog visibility
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = sheetState,
        containerColor = Color.White,
        onDismissRequest = { updateDialogDisplayStatus(false) }
    ) {
        // Content inside the Bottom Sheet
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AsyncImage(
                    model = coinUi.iconUrl,
                    contentDescription = coinUi.name,
                    modifier = Modifier.size(50.dp)
                )
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row {
                        Text(
                            text = coinUi.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = " (${coinUi.symbol})",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.Black
                        )
                    }
                    Row {
                        Text(
                            text = "PRICE",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.DarkGray
                        )
                        Text(
                            text = "$ ${coinUi.price.formatted}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.DarkGray
                        )
                    }
                    Row {
                        Text(
                            text = "MARKET CAP",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.DarkGray
                        )
                        Text(
                            text = "$ ${coinUi.price.formatted}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.DarkGray
                        )
                    }
                }
            }

            Text(
                text = coinDetail.description,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.LightGray)
            )

            Text(
                text = "GO TO WEBSITE",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = blueColor,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(coinDetail.websiteUrl))
                        context.startActivity(intent)
                    }
            )
        }
    }

    // Launch the bottom sheet dialog when showDialog is set to true
    LaunchedEffect(sheetState) {
        if (showDialog) {
            scope.launch { sheetState.show() }
        }
    }
}
