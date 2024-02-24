package com.example.tvshowapp.data.localdb

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tvshowapp.domain.model.TvShow
import kotlinx.coroutines.flow.Flow


@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavTvShow(tvShow: TvShow) : Long

    @Query("SELECT * from TvShow")
    suspend fun tvShowList(): List<TvShow>

    @Query("Delete from TvShow where id=:id")
    suspend fun removeFavShow(id: Int)
}