package com.assignment.quotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.assignment.quote.realm.services.quoteService
import com.assignment.quotes.models.Quote
import com.assignment.quotes.networkLayer.ApiService
import com.assignment.quotes.networkLayer.QResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmResults


class QuotesViewModel : ViewModel() {

    val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    fun quotesObservable(): LiveData<RealmResults<Quote>> {
        return realm.quoteService().quotesObservable()
    }

    fun quotesDataAlreadExist(): Boolean {
        return realm.quoteService().quotesSize() != 0
    }

    fun fetchQuotes() {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            ApiService.buildService().fetchQuotes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { response -> onResponse(response) })
    }

    fun onResponse(response: QResponse){
        if(response.statusCode == 200){
            if(response.quotes!!.size != 0)
                realm.quoteService().addList(response.quotes!!)
        }
    }

}