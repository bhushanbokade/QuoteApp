package com.bhupendra.quote.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.bhupendra.quote.MainDispatcherRule
import com.bhupendra.quote.models.QuoteListItem
import com.bhupendra.quote.repository.QuoteRepository
import com.bhupendra.quote.utils.category
import com.bhupendra.quote.viewmodels.DetailViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    private lateinit var repository: QuoteRepository
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var viewModel: DetailViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        savedStateHandle = mockk(relaxed = true)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun testEmptyQuotes() {
        val testQuotes = MutableStateFlow<List<QuoteListItem>>(emptyList())

        every { repository.quotes } returns testQuotes

        viewModel = DetailViewModel(repository, savedStateHandle)

        assertEquals(testQuotes, viewModel.quotes)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun testQuotes() {
        val mockQuotes = listOf(
            QuoteListItem(category, "Android Quote 1"),
            QuoteListItem(category, "Android Quote 2")
        )

        val testQuotes = MutableStateFlow(mockQuotes)

        every { repository.quotes } returns testQuotes

        viewModel = DetailViewModel(repository, savedStateHandle)

        assertEquals(testQuotes, viewModel.quotes)
    }
}
