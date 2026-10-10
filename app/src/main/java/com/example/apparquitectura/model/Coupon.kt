package com.example.apparquitectura.model

import com.google.gson.JsonObject
import java.io.Serializable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Coupon(couponJson: JsonObject?) : Serializable {

    var id: String = ""
    var image_url: String = ""
    var title: String = ""
    var descriptionShort: String = ""
    var category: String = ""
    var description: String = ""
    var offer: String = ""
    var website: String = ""
    var endDate: String = ""
    var url: String = ""

    init {
        id                  = getString(couponJson, ID)
        image_url           = getString(couponJson, IMAGE_URL)
        title               = getString(couponJson, TITLE)
        descriptionShort    = chunkWords(getString(couponJson, DESCRIPTION_SHORT), ' ', 6)
        category            = chunkWords(getString(couponJson, CATEGORY), ',', 2)
        description         = getString(couponJson, DESCRIPTION)
        offer               = getString(couponJson, OFFER)
        website             = getString(couponJson, WEBSITE)
        endDate             = getFormatDate(getString(couponJson, END_DATE))
        url                 = getString(couponJson, URL)
    }

    companion object {
        private val ID                  = "lmd_id"
        private val IMAGE_URL           = "image_url"
        private val TITLE               = "title"
        private val DESCRIPTION_SHORT   = "offer_text"
        private val CATEGORY            = "categories"
        private val DESCRIPTION         = "description"
        private val OFFER               = "offer"
        private val WEBSITE             = "store"
        private val END_DATE            = "end_date"
        private val URL                 = "url"
    }

    // Un campo ausente o nulo queda como cadena vacía, sin afectar al resto del cupón
    private fun getString(json: JsonObject?, key: String): String {
        val element = json?.get(key)
        return if (element != null && element.isJsonPrimitive) element.asString else ""
    }

    private fun getFormatDate(dateCoupon: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return try {
            val parsedDate = format.parse(dateCoupon)
            if (parsedDate != null) dateFormat.format(parsedDate) else ""
        } catch (e: ParseException) {
            ""
        }
    }

    // Devuelve como mucho las primeras `quantity` partes del texto
    private fun chunkWords(string: String, delimiter: Char, quantity: Int): String {
        return string.split(delimiter)
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .take(quantity)
            .joinToString(" ")
    }
}
