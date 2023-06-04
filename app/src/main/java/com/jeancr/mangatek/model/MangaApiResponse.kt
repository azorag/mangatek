package com.jeancr.mangatek.model

import com.google.gson.annotations.SerializedName

data class MangaApiResponse(

	@field:SerializedName("product")
	val product: Product? = null
)

data class BarcodeFormats(

	@field:SerializedName("ean_8")
	val ean8: String? = null
)

data class OnlineStoresItem(

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class Attributes(

	@field:SerializedName("size")
	val size: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("weight")
	val weight: String? = null,

	@field:SerializedName("mpn")
	val mpn: String? = null,

	@field:SerializedName("model")
	val model: String? = null,

	@field:SerializedName("asin")
	val asin: String? = null,

	@field:SerializedName("dimensions")
	val dimensions: String? = null
)

data class Product(

	@field:SerializedName("barcode_formats")
	val barcodeFormats: BarcodeFormats? = null,

	@field:SerializedName("features")
	val features: List<String?>? = null,

	@field:SerializedName("images")
	val images: List<String?>? = null,

	@field:SerializedName("artist")
	val artist: Any? = null,

	@field:SerializedName("description")
	val description: Any? = null,

	@field:SerializedName("ingredients")
	val ingredients: Any? = null,

	@field:SerializedName("attributes")
	val attributes: Attributes? = null,

	@field:SerializedName("category")
	val category: Any? = null,

	@field:SerializedName("online_stores")
	val onlineStores: List<OnlineStoresItem?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("brand")
	val brand: Any? = null,

	@field:SerializedName("manufacturer")
	val manufacturer: String? = null
)
