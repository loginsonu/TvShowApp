package com.example.tvshowapp.data.remote.dto.details_tv_shows_dto

import com.google.gson.annotations.SerializedName

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String?,
    val name: String?
)