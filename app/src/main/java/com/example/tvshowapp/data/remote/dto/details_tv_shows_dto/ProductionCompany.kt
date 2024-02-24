package com.example.tvshowapp.data.remote.dto.details_tv_shows_dto

import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    val id: Int?,
    @SerializedName("logo_path")
    val logoPath: String?,
    val name: String?,
    @SerializedName("origin_country")
    val originCountry: String?
)