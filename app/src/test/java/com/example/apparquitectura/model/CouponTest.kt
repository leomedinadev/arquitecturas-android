package com.example.apparquitectura.model

import com.google.gson.JsonObject
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class CouponTest {

    private fun couponJson(): JsonObject {
        val json = JsonObject()
        json.addProperty("lmd_id", "123")
        json.addProperty("image_url", "https://example.com/coupon.png")
        json.addProperty("title", "Descuento de verano")
        json.addProperty("offer_text", "uno dos tres cuatro cinco seis siete ocho")
        json.addProperty("categories", "Moda,Ropa,Zapatos")
        json.addProperty("description", "Descripcion completa")
        json.addProperty("offer", "20% OFF")
        json.addProperty("store", "example.com")
        json.addProperty("end_date", "2026-12-31")
        json.addProperty("url", "https://example.com/oferta")
        return json
    }

    @Test
    fun parsesAllFields() {
        val coupon = Coupon(couponJson())

        assertEquals("123", coupon.id)
        assertEquals("https://example.com/coupon.png", coupon.image_url)
        assertEquals("Descuento de verano", coupon.title)
        assertEquals("Descripcion completa", coupon.description)
        assertEquals("20% OFF", coupon.offer)
        assertEquals("example.com", coupon.website)
        assertEquals("https://example.com/oferta", coupon.url)
    }

    @Test
    fun shortensDescriptionAndCategories() {
        val coupon = Coupon(couponJson())

        assertEquals("uno dos tres cuatro cinco seis", coupon.descriptionShort)
        assertEquals("Moda Ropa", coupon.category)
    }

    @Test
    fun formatsEndDate() {
        val expected = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            .format(GregorianCalendar(2026, Calendar.DECEMBER, 31).time)

        assertEquals(expected, Coupon(couponJson()).endDate)
    }

    @Test
    fun keepsOtherFieldsWhenTextIsShort() {
        val json = couponJson()
        json.addProperty("offer_text", "Oferta")
        json.addProperty("categories", "Moda")

        val coupon = Coupon(json)

        assertEquals("Oferta", coupon.descriptionShort)
        assertEquals("Moda", coupon.category)
        assertEquals("https://example.com/oferta", coupon.url)
    }

    @Test
    fun missingOrInvalidFieldsBecomeEmpty() {
        val json = JsonObject()
        json.addProperty("title", "Solo titulo")
        json.addProperty("end_date", "sin fecha")

        val coupon = Coupon(json)

        assertEquals("Solo titulo", coupon.title)
        assertEquals("", coupon.image_url)
        assertEquals("", coupon.endDate)
        assertEquals("", Coupon(null).title)
    }
}
