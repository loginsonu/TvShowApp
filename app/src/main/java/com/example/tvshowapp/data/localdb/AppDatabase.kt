package com.example.tvshowapp.data.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tvshowapp.common.Constants
import com.example.tvshowapp.domain.model.TvShow
import retrofit2.Converter


@Database(
    entities = [TvShow::class] ,
    version = 1,
    exportSchema = false
)

abstract class AppDatabase  : RoomDatabase() {

    abstract fun getDao() : AppDao

    companion object{

        @Volatile private var instance : AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            Constants.dbName
        ).build()

    }
}