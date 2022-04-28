package com.example.mvi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvi.ui.theme.White100
import com.example.mvi.ui.theme.fonts
import com.example.mvi.R

@Preview()
@Composable
fun SavingsCard() {
    Card(
        modifier = Modifier.wrapContentHeight(),
        shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp),
        backgroundColor = White100,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(
                    colorResource(id = R.color.teal_200),
                    shape = RoundedCornerShape(18.dp)
                )
                .padding(10.dp)
        ) {
            Text(
                text = "₹81 total savings", fontFamily = fonts,
                color = colorResource(id = R.color.teal_700),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "with TRYNEW coupon", fontFamily = fonts,
                color = colorResource(id = R.color.teal_700),
                fontSize = 14.sp
            )
        }
    }

}