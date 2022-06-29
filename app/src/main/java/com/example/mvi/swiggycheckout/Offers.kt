package com.example.mvi

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvi.ui.theme.fonts
//import com.example.mvi.R

@Composable
fun OffersBenefits() {
    Text(
        modifier = Modifier.padding(start = 16.dp, top = 30.dp),
        text = "Offers & Benefits",
        fontFamily = fonts,
        color = colorResource(id = R.color.black),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )
}