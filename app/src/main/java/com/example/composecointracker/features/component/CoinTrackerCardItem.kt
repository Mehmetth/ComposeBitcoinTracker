package com.example.composecointracker.features.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.composecointracker.R
import com.example.composecointracker.data.model.Coin
import com.example.composecointracker.features.theme.BLUE
import com.example.composecointracker.features.theme.PRICE_DOWN
import com.example.composecointracker.features.theme.PRICE_UP


@Composable
fun CoinTrackerCardItem(
    dto: Coin,
    detailClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { detailClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = dto.image,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(shape = RoundedCornerShape(15)),
                        contentScale = ContentScale.Crop,
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "$ ${dto.current_price.toString()}",
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(
                                if ((dto.price_change_24h ?: 0.0) > 0.0) PRICE_UP else PRICE_DOWN
                            )
                            .padding(vertical = 5.dp, horizontal = 10.dp),
                        fontSize = 15.sp,
                        color = Color.White
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                    Column {
                        Text(
                            text = dto.name.orEmpty(),
                            style = TextStyle(
                                fontSize = 17.sp
                            ),
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                        Text(
                            text = dto.symbol.orEmpty(),
                            style = TextStyle(
                                fontSize = 13.sp
                            ),
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        modifier = Modifier.clickable {
                            expandedState = !expandedState
                        }
                    ) {
                        Text(
                            modifier = Modifier,
                            text = if (expandedState) stringResource(id = R.string.less) else stringResource(
                                id = R.string.more
                            ),
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.ic_down_arrow),
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier
                                .rotate(rotationState)
                                .size(20.dp)
                        )
                    }
                }

                if (expandedState) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp), color = BLUE
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "$ ${dto.high_24h}")
                            Text(
                                text = stringResource(id = R.string.highest),
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "$ ${dto.low_24h}")
                            Text(
                                text = stringResource(id = R.string.lowest),
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "% ${dto.price_change_percentage_24h}")
                            Text(
                                text = stringResource(id = R.string.change),
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}