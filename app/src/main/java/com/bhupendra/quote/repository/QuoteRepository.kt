package com.bhupendra.quote.repository

import com.bhupendra.quote.api.QuoteAPI
import com.bhupendra.quote.models.QuoteListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val quoteAPI: QuoteAPI) {

    private val _quotes = MutableStateFlow<List<QuoteListItem>>(emptyList())
    val quotes: StateFlow<List<QuoteListItem>> = _quotes

    suspend fun getQuotes(category: String) {
        val result = quoteAPI.getQuotes("quote[?(@.category==\"${category}\")]")
        if (result.isSuccessful) {
            result.body()?.let {
                _quotes.emit(it)
            }
        }
    }
}