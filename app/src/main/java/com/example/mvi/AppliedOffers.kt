package com.example.mvi

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvi.ui.theme.White100
import com.example.mvi.ui.theme.fonts
import com.example.mvi.R

val inlineContentMap = mapOf(
    "imageId" to InlineTextContent(
        Placeholder(20.sp, 15.sp, PlaceholderVerticalAlign.TextCenter)
    ) {
        Image(
            modifier = Modifier.size(15.dp),
            alignment = Alignment.Center,
            painter = painterResource(id = R.drawable.ic_baseline_done_24),
            contentDescription = "Tick Icon",
            colorFilter = ColorFilter.tint(
                color = colorResource(id = R.color.orange),
            )
        )
    }
)

@Preview
@Composable
fun AppliedOffers() {
    val annotatedString = buildAnnotatedString {
        appendInlineContent(id = "imageId")
        withStyle(
            style = SpanStyle(
                colorResource(id = R.color.orange),
                fontFamily = fonts,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("₹81 ")
        }
        withStyle(style = SpanStyle(Color.Gray, fontFamily = fonts, fontWeight = FontWeight.Bold)) {
            append("coupon savings")
        }

    }
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .padding(12.dp),
        elevation = 0.dp,
        shape = RoundedCornerShape(14.dp),
        backgroundColor = White100,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp, start = 15.dp, end = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "'TRYNEW' applied ", fontFamily = fonts,
                    color = colorResource(id = R.color.black),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    textAlign = TextAlign.Center,
                    text = annotatedString, fontFamily = fonts,
                    fontSize = 14.sp,
                    inlineContent = inlineContentMap
                )
            }
            Text(
                textAlign = TextAlign.Center,
                text = "Remove", fontFamily = fonts,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.orange)
            )
        }

    }

}