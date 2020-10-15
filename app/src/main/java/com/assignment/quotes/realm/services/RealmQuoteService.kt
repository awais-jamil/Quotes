package com.assignment.quote.realm.services

import androidx.lifecycle.LiveData
import com.assignment.quote.realm.base.asLiveData
import com.assignment.quotes.models.Quote
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults


class RealmQuoteService(val realm: Realm) {

    fun addList(list: List<Quote>){
        realm.executeTransaction { realm ->
            realm.insertOrUpdate(list)
        }
    }

    fun quotesObservable(): LiveData<RealmResults<Quote>> {
        //listener

        return realm.where(Quote::class.java)
            .findAllAsync().asLiveData()
    }

    fun quotesSize(): Int {
        //listener

        return realm.where(Quote::class.java)
            .findAll().size
    }

}

fun Realm.quoteService() : RealmQuoteService = RealmQuoteService(this)