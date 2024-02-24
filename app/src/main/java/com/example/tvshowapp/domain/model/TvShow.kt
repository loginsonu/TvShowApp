package com.example.tvshowapp.domain.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["id"], unique = true)])
data class TvShow(
    val id: Int,
    val name: String,
    val posterPath: String
){
    @PrimaryKey(autoGenerate = true)
    var showId: Int = 0
}
