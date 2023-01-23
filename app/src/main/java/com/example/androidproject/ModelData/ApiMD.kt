package com.example.androidproject.ModelData

import com.google.gson.annotations.SerializedName

data class ApiMD(

	@field:SerializedName("ApiMD")
	val apiMD: List<ApiMDItem?>? = null
)

data class ApiMDItem(

	@field:SerializedName("albumId")
	val albumId: String = null.toString(),

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("thumbnailUrl")
	val thumbnailUrl: String? = null
)
