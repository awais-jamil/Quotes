package com.assignment.quotes.support

import android.app.Application
import io.realm.Realm


class QApplication: Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }

    companion object {
        private var instance: QApplication? = null

        @JvmStatic
        fun applicationContext() : QApplication {
            return instance as QApplication
        }
    }
}