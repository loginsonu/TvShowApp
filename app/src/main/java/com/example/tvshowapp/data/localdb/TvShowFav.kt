package com.example.tvshowapp.data.localdb

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["id"], unique = true)])
data class TvShowFav(
    val id: Int,
    val name: String,
    val posterPath: String
){
    @PrimaryKey(autoGenerate = true)
    var showId: Int = 0
}
