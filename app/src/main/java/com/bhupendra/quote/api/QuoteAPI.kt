package com.bhupendra.quote.api

import com.bhupendra.quote.models.QuoteListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface QuoteAPI {
    @GET("/v3/b/66f3f2e3e41b4d34e437210a?meta=false")
    suspend fun getQuotes(@Header("X-JSON-Path") category: String): Response<List<QuoteListItem>>
}