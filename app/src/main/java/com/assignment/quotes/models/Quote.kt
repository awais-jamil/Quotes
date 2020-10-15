package com.assignment.quotes.models

import io.realm.RealmObject
import java.io.Serializable
import java.util.*

open class Quote(
    var _id:String = UUID.randomUUID().toString(),
    var quoteText:String = "",
    var quoteAuthor: String = ""): Serializable, RealmObject(){

    fun hashMap(): HashMap<String, Any> {

        return hashMapOf<String, Any>(

            "id" to _id,
            "quoteText" to quoteText,
            "quoteAuthor" to quoteAuthor,
        )
    }

    override fun equals(other: Any?): Boolean {

        other?.let {

            return _id.equals((it as Quote)._id)
        }

        return false
    }

    override fun hashCode(): Int {
        return _id.hashCode()
    }
}