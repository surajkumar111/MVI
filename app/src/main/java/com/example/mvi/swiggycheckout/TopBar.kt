package com.example.mvi

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvi.ui.theme.fonts
import com.example.mvi.R

@Preview
@Composable
fun TopBar() {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = Color.White,
        elevation = 0.dp
    ) {
        Icon(
            modifier = Modifier.padding(start = 10.dp),
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
            contentDescription = "back arrow", tint = Color.Gray

        )
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "The Hideout Cafe",
            fontFamily = fonts,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            fontSize = 16.sp
        )
    }
}