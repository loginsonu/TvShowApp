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
    suspend fun addFavTvShow(tvShow: TvShowFav) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTvShowList(tvShow: List<TvShow>) : Array<Long>

    @Query("Delete from TvShow")
    suspend fun deleteTvShowList()

    @Query("SELECT * from TvShowFav")
    suspend fun tvShowList(): List<TvShowFav>

    @Query("Delete from TvShowFav where id=:id")
    suspend fun removeFavShow(id: Int)

    @Query("SELECT * from TvShow")
    suspend fun getListTvShow():List<TvShow>

}