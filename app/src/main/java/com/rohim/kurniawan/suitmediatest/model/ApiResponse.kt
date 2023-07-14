package com.rohim.kurniawan.suitmediatest.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(

	@field:SerializedName("per_page")
	val perPage: Int,

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("support")
	val support: Support
)

data class Support(

	@field:SerializedName("text")
	val text: String,

	@field:SerializedName("url")
	val url: String
)

data class DataItem(

	@field:SerializedName("last_name")
	val lastName: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("avatar")
	val avatar: String,

	@field:SerializedName("first_name")
	val firstName: String,

	@field:SerializedName("email")
	val email: String
)
