package com.example.mvi.searchFeature

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvi.R

class SearchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var searchData = mutableListOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as SearchViewHolder).onBind(searchData[position])

    }

    override fun getItemCount(): Int {
        return searchData.size
    }


    fun addData(list: List<String>) {
        searchData.clear()
        searchData = (list as? MutableList<String> ?: emptyList<String>()).toMutableList()
        notifyDataSetChanged()

    }

}


class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun onBind(text: String) {
        val textView = itemView.findViewById<TextView>(R.id.textView);
        textView.text = text

    }

}