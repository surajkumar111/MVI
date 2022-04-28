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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvi.ui.theme.White100
import com.example.mvi.ui.theme.fonts
//import com.example.mvi.R

@Preview
@Composable
fun BottomSheet() {
    Card(
        modifier = Modifier
            .wrapContentHeight(),
        shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp),
        backgroundColor = White100,
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 10.dp, start = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Deliver to Home | 55 MINS ", fontFamily = fonts,
                        color = colorResource(id = R.color.black),
                        fontWeight=FontWeight.Bold,
                        fontSize = 17.sp
                    )
                    Text(
                        textAlign = TextAlign.Center,
                        color = Color.Gray,
                        text = "Jamshedpur, Jharkhand...", fontFamily = fonts,
                        fontSize = 14.sp,
                    )
                }
                Card(
                    shape = RoundedCornerShape(12.dp),
                    elevation = 0.dp,
                    border = BorderStroke(1.dp, colorResource(id = R.color.light_grey))
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Image(
                            modifier = Modifier
                                .size(25.dp)
                                .padding(start = 5.dp),
                            painter = painterResource(id = R.drawable.home),
                            colorFilter = ColorFilter.tint(color = colorResource(id = R.color.orange)),
                            contentDescription = "home"
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24),
                            colorFilter = ColorFilter.tint(color = colorResource(id = R.color.orange)),
                            contentDescription = "drop down"
                        )
                    }
                }
            }

            DrawDash()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp, start = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "₹ 120 ", fontFamily = fonts,
                        color = colorResource(id = R.color.black),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        textAlign = TextAlign.Center,
                        text = "View Detailed Bill", fontFamily = fonts,
                        color = colorResource(id = R.color.teal_700),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                    )
                }
                Button(
                    onClick = {  },
                    Modifier
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.teal_700))
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Proceed to Pay", fontFamily = fonts,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.white)
                    )
                }

            }

        }
    }

}