package com.example.mvi.searchFeature

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class SearchManager (
    val lifecycle: LifecycleOwner?,
    private val recyclerView: RecyclerView,
    searchView: androidx.appcompat.widget.SearchView,
    var searchViewModel: SearchViewModel,
) :
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private val searchAdapter = SearchAdapter()


    init {
        searchView.setOnQueryTextListener(this@SearchManager)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = searchAdapter

        setObserver()

    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        if (newText?.length == 0) {
            addData(emptyList())
            return true
        }
        searchViewModel.getSearchAutoCompleteResponse(newText ?: "")

        return true

    }

    private fun addData(mlist: List<String>) {
        searchAdapter.addData(mlist)
    }

    fun setObserver() {
        lifecycle?.let { it ->
            searchViewModel.searchLiveData.observe(it) { items ->
                addData(items)

            }
        }
    }
}