package com.bhupendra.quote.repository

import com.bhupendra.quote.api.QuoteAPI
import com.bhupendra.quote.models.QuoteListItem
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class QuoteRepositoryTest {

    private lateinit var quoteAPI: QuoteAPI
    private lateinit var quoteRepository: QuoteRepository

    @Before
    fun setup() {
        quoteAPI = mockk()
        quoteRepository = QuoteRepository(quoteAPI)
    }

    @Test
    fun testGetQuotes() = runBlocking {
        val category = "Android"
        val mockQuotes = listOf(
            QuoteListItem(category, "Android Quote 1"),
            QuoteListItem(category, "Android Quote 2")
        )
        val mockResponse = mockk<Response<List<QuoteListItem>>>()

        coEvery { quoteAPI.getQuotes(any()) } returns mockResponse
        coEvery { mockResponse.isSuccessful } returns true
        coEvery { mockResponse.body() } returns mockQuotes

        quoteRepository.getQuotes(category)

        val emittedQuotes = quoteRepository.quotes.first()
        assertEquals(mockQuotes, emittedQuotes)
    }

    @Test
    fun testGetEmptyQuote() = runBlocking {
        val category = "android"
        val mockResponse = mockk<Response<List<QuoteListItem>>>()

        coEvery { quoteAPI.getQuotes(any()) } returns mockResponse
        coEvery { mockResponse.isSuccessful } returns false

        quoteRepository.getQuotes(category)

        val emittedQuotes = quoteRepository.quotes.first()
        assertEquals(emptyList<QuoteListItem>(), emittedQuotes)
    }
}