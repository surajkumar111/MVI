package com.example.mvi.searchFeature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel  // used for viewModel constructor injection
class SearchViewModel @Inject constructor(
    var searchRepo: SearchRepo,
) : ViewModel() {

    private var _searchLiveData = MutableLiveData<List<String>>()
    val searchLiveData: LiveData<List<String>>
        get() = _searchLiveData

    fun getSearchAutoCompleteResponse(text: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                searchRepo.getCountries(text)?.let {
                    val nameMap = mutableListOf<String>()
                    it.forEach {
                        nameMap.add(it.name.common)
                    }
                    _searchLiveData.value = nameMap.take(5)
                }
            }
        }
    }

}