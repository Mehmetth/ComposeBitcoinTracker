package com.example.composebitcointracker.features.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.composebitcointracker.R
import com.example.composebitcointracker.features.theme.ComposeBitcoinTrackerTheme

@Composable
fun BitcoinTrackerSearchBar(
    placeholder: String,
    onTextChange: (text: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val searchValue = rememberSaveable { mutableStateOf("") }

    Box(
        modifier = modifier
    ) {
        TextField(
            value = searchValue.value,
            onValueChange = {
                searchValue.value = it
                onTextChange(it)
            },
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxSize()
                .shadow(elevation = 10.dp)
                .clip(RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black
            )
        )
    }
}

@Preview
@Composable
fun PreviewMinimaSearchBar() {
    ComposeBitcoinTrackerTheme {
        BitcoinTrackerSearchBar(
            placeholder = stringResource(id = R.string.search),
            onTextChange = {},
            modifier = Modifier
                .height(50.dp)
                .zIndex(1f)
        )
    }
}