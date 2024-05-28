//package com.example.pooldiary.utils
//
//import khttp.responses.Response
//import org.jsoup.Jsoup
//import org.jsoup.nodes.Document
//
//class WebScraper(private val url: String) {
//    var name: String? = null
//    var imgUrl: String? = null
//    var prices: MutableList<Int> = mutableListOf()
//
//    fun getInfo() {
//        val response: Response = khttp.get(url)
//        val document: Document = Jsoup.parse(response.text)
//
//        name = document.select("#primary > section:nth-child(1) > div > div > div:nth-child(1) > div:nth-child(2) > h1").text()
//        imgUrl = document.select("body > main > section:nth-child(1) > div > div > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div > div:nth-child(1) > a > img").attr("src")
//
//        try {
//            val productVariations = document.select("#primary > section:nth-child(1) > div > div > div:nth-child(1) > div:nth-child(2) > form").attr("data-product_variations")
//            val variations = org.json.JSONArray(productVariations)
//            for (i in 0 until variations.length()) {
//                val item = variations.getJSONObject(i)
//                prices.add(item.getInt("display_price"))
//            }
//        } catch (e: IndexOutOfBoundsException) {
//            val priceElements = document.select("#primary > section:nth-child(1) > div > div > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > p").first().children()
//            priceElements.forEachIndexed { index, element ->
//                val rawPrice = element.select("span:nth-child(${index + 1}) > bdi").text().filter { it.isDigit() }.toInt()
//                prices.add(rawPrice)
//            }
//        }
//
//        prices.sort()
//    }
//}
