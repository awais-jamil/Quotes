package com.assignment.quotes.networkLayer

import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {
    @GET("quotes?page=1&limit=10")
    fun fetchQuotes() : Single<QResponse>
}