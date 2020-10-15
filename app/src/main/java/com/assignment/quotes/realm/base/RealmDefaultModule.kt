package com.assignment.quote.realm.base

import com.assignment.quotes.models.Quote
import io.realm.annotations.RealmModule

@RealmModule(classes = [Quote::class])
class RealmDefaultModule