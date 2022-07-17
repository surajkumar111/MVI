package com.example.mvi

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun temp() = CoroutineScope(Dispatchers.IO).launch {
        Log.i("infoCour","get Data Begin ${Thread.currentThread().name}")
        for(i in 1..5)
        {
            getData()
            Log.i("infoCour","get Data Next${Thread.currentThread().name}")

        }
        assertEquals(2,2)


    }

    @Test
    fun getData(){
        Thread.sleep(3000)
        Log.i("infoCour","get Data Successfull ${Thread.currentThread().name}")
    }
}