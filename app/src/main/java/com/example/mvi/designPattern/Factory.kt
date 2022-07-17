package com.example.mvi.designPattern


sealed class Country {
    object Canada : Country()
}

object Spain : Country()
data class USA(val someProperty: String) : Country()

class Currency(
    val code: String
)

class Factory {
    fun currencyForCountry(country: Country): Currency {
        return when (country) {
            Country.Canada -> Currency("EUR")
            Spain -> Currency("EUR")
            is USA -> Currency("USD")
        }
    }
}

