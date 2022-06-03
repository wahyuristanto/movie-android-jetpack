package skycode.dicoding.jetpack.mvvm.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import skycode.dicoding.jetpack.mvvm.data.local.db.dao.MovieDao
import skycode.dicoding.jetpack.mvvm.data.local.db.dao.TvShowDao
import skycode.dicoding.jetpack.mvvm.data.model.db.MovieEntity
import skycode.dicoding.jetpack.mvvm.data.model.db.TvShowEntity

/**
 * Created by wahyu on 28/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
}