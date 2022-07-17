package com.example.mvi.designPattern

import java.text.FieldPosition

/****  converts interface of a class into another interface the client expects ***/
/****  converts data from one format to other ***/

data class DisplayData(val index: Float, val data: String)
class DataDisplay {
    fun displayData(data: DisplayData) {

    }
}

data class DatabaseData(val position: Int, val amount: Int)
class DatabaseGenerator() {
    fun generateData(): List<DatabaseData> {
        val list = arrayListOf<DatabaseData>()
        list.add(DatabaseData(1, 3))
        list.add(DatabaseData(2, 4))
        return list
    }
}


interface DatabaseDataConverter {
    fun convertData(data: List<DatabaseData>): List<DisplayData>
}

class Adapter(val display: DataDisplay) : DatabaseDataConverter {
    override fun convertData(data: List<DatabaseData>): List<DisplayData> {
        val returnList = arrayListOf<DisplayData>()
        for (datum in data) {
            val ddt = DisplayData(datum.position.toFloat(), datum.amount.toString())
            display.displayData(ddt)
            returnList.add(ddt)
        }
        return returnList
    }

}