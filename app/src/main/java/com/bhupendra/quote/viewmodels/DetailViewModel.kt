package com.bhupendra.quote.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhupendra.quote.models.QuoteListItem
import com.bhupendra.quote.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: QuoteRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val quotes: StateFlow<List<QuoteListItem>> = repository.quotes

    init {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category") ?: ""
            repository.getQuotes(category)
        }
    }
}