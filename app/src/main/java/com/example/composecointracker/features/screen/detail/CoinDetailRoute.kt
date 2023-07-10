package com.example.composecointracker.features.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.composecointracker.features.theme.DETAIL_TOP_BLUE

@Composable
fun CoinDetailRoute(
    viewModel: CoinDetailViewModel = hiltViewModel(),
) {
    val viewState = viewModel.state.collectAsState().value

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                else -> {}
            }
        }
    }

    CoinDetailScreen(
        viewState,
        onViewEvent = viewModel::setEvent
    )
}

@Composable
private fun CoinDetailScreen(
    viewState: CoinDetailState,
    onViewEvent: (CoinDetailEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(DETAIL_TOP_BLUE)
        ) {
            Text(
                text = "Coin Detail",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Divider(modifier = Modifier.fillMaxWidth(), color = Color.White)
            Row() {
                AsyncImage(
                    model = viewState.coinDetail?.image?.large,
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                        .padding(start = 15.dp)
                        .size(75.dp)
                        .clip(shape = RoundedCornerShape(15)),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )

                Column(
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                        .padding(start = 10.dp)
                ) {
                    Text(
                        text = viewState.coinDetail?.name.orEmpty(),
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "(${viewState.coinDetail?.symbol.orEmpty()})",
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                        .padding(horizontal = 10.dp),
                    horizontalAlignment = Alignment.End,
                ) {
                    Row {
                        Text(
                            text = "Current Price : ",
                            modifier = Modifier,
                            textAlign = TextAlign.Center,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "$ ${viewState.coinDetail?.market_data?.current_price?.usd}",
                            modifier = Modifier,
                            textAlign = TextAlign.Center,
                            fontSize = 17.sp,
                            color = Color.White
                        )
                    }
                    Row {
                        Text(
                            text = "Highest 24h : ",
                            modifier = Modifier,
                            textAlign = TextAlign.Center,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "$ ${viewState.coinDetail?.market_data?.high_24h?.usd}",
                            modifier = Modifier,
                            textAlign = TextAlign.Center,
                            fontSize = 17.sp,
                            color = Color.White
                        )
                    }
                    Row {
                        Text(
                            text = "Lowest 24h : ",
                            modifier = Modifier,
                            textAlign = TextAlign.Center,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "$ ${viewState.coinDetail?.market_data?.low_24h?.usd}",
                            modifier = Modifier,
                            textAlign = TextAlign.Center,
                            fontSize = 17.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
        LazyColumn {
            item {
                Column(modifier = Modifier.padding(15.dp)) {
                    Text(
                        text = "Genesis Date",
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = viewState.coinDetail?.genesis_date.orEmpty(),
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Black,
                    )
                }
                Divider(modifier = Modifier.fillMaxWidth(), color = DETAIL_TOP_BLUE)
            }
            item {
                Column(modifier = Modifier.padding(15.dp)) {
                    Text(
                        text = "Hashing Algorithm",
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = viewState.coinDetail?.hashing_algorithm.orEmpty(),
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Black,
                    )
                }
                Divider(modifier = Modifier.fillMaxWidth(), color = DETAIL_TOP_BLUE)
            }
            item {
                Column(modifier = Modifier.padding(15.dp)) {
                    Text(
                        text = "Description",
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = viewState.coinDetail?.description?.en.orEmpty(),
                        modifier = Modifier,
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp,
                        color = Color.Black,
                    )
                }
                Divider(modifier = Modifier.fillMaxWidth(), color = DETAIL_TOP_BLUE)
            }
        }
    }
}