package com.assignment.quotes.networkLayer

import com.assignment.quotes.models.Quote

class QResponse {
    var statusCode : Int = 0
    var quotes: List<Quote>? = null
}