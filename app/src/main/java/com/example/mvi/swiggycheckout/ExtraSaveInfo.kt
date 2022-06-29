package com.example.mvi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvi.ui.theme.fonts
import com.example.mvi.R

@Preview
@Composable
fun ExtraSaveInfo() {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                colorResource(id = R.color.black),
                fontFamily = fonts,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("Save ₹24 Extra on this order! ")
        }

    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 8.dp, bottom = 20.dp, start = 15.dp, end = 15.dp)
            .background(
                shape = RoundedCornerShape(14.dp),
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.White,
                        colorResource(id = R.color.white),
                        colorResource(id = R.color.orange_50)
                    )
                )
            )
            .fillMaxWidth()
            .padding(20.dp)
            .wrapContentHeight(),

        ) {
        Image(
            modifier = Modifier.height(15.dp),
            alignment = Alignment.Center,
            painter = painterResource(id = R.drawable.one),
            contentDescription = "",
            colorFilter = ColorFilter.tint(
                color = colorResource(id = R.color.orange),
            )
        )
        Text(
            modifier = Modifier.padding(start = 5.dp),
            text = annotatedString, fontFamily = fonts,
            fontSize = 16.sp
        )

    }

}