package com.example.mvi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvi.ui.theme.fonts
import com.example.mvi.R

@Preview
@Composable
fun ItemView() {
    Column(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, top = 20.dp)
            .background(
                shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp),
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.White,
                        colorResource(id = R.color.teal_100),
                        colorResource(id = R.color.teal_150)
                    )
                )
            )
            .fillMaxWidth()
            .padding(20.dp)
            .wrapContentHeight(),

        ) {
        Text(
            text = "TRYNEW eligible items", fontFamily = fonts,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.teal_700),
            fontSize = 12.sp
        )


        repeat(cardList.size) {
            ItemCard()
        }

    }

}