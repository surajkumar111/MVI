package com.example.mvi

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvi.ui.theme.fonts
import com.example.mvi.R

@Preview
@Composable
fun ItemCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Image(
            modifier = Modifier.weight(0.1f),
            painter = painterResource(id = R.drawable.non_veg),
            contentDescription = "non veg icon"
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .width(100.dp)
                .weight(0.5f),
        ) {
            Text(
                text = "Double Chicken Egg Roll",
                fontFamily = fonts,
                color = colorResource(id = R.color.dark_grey),
                fontSize = 14.sp
            )
            Row(
                modifier = Modifier
                    .padding(top = 5.dp),
            ) {
                Text(
                    text = "Customize",
                    fontFamily = fonts,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 12.sp
                )
                Image(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .height(15.dp),
                    painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24),
                    colorFilter = ColorFilter.tint(color = colorResource(id = R.color.black)),
                    contentDescription = "drop down"
                )
            }

        }
        Card(
            modifier = Modifier.weight(0.3f),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, colorResource(id = R.color.light_grey))
        ) {
            Row(
                modifier = Modifier.padding(7.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painter = painterResource(id = R.drawable.minus),
                    colorFilter = ColorFilter.tint(color = colorResource(id = R.color.teal_700)),
                    contentDescription = "minus"
                )
                Text(
                    text = "1",
                    fontFamily = fonts,
                    color = colorResource(id = R.color.teal_700),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Image(
                    modifier = Modifier.size(15.dp),
                    painter = painterResource(id = R.drawable.plus),
                    colorFilter = ColorFilter.tint(color = colorResource(id = R.color.teal_700)),
                    contentDescription = "plus"
                )
            }

        }

        Column(
            modifier = Modifier
                .padding(start = 20.dp)
                .weight(0.2f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = " ₹ 162",
                style = TextStyle(textDecoration = TextDecoration.LineThrough),
                fontFamily = fonts,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp
            )
            Text(
                text = "₹ 81",
                fontFamily = fonts,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }

    }

}