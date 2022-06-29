package com.example.mvi.swiggycheckout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.mvi.*
import com.example.mvi.ui.theme.MVITheme

val cardList = listOf(1)

class SwiggyCheckout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVITheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopBar() },
                    backgroundColor = colorResource(id = R.color.bg),
                    bottomBar = { BottomSheet() }
                ) {
                    Column(
                        Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = it.calculateBottomPadding())
                    ) {
                        SavingsCard()
                        ItemView()
                        WriteInstCard()
                        OffersBenefits()
                        AppliedOffers()
                        ExtraSaveInfo()

                    }

                }
            }
        }
    }

}



