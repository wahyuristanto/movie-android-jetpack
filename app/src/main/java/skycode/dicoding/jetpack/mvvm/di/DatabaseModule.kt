package skycode.dicoding.jetpack.mvvm.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import skycode.dicoding.jetpack.mvvm.data.local.db.AppDatabase
import skycode.dicoding.jetpack.mvvm.data.local.db.dao.MovieDao
import skycode.dicoding.jetpack.mvvm.data.local.db.dao.TvShowDao
import skycode.dicoding.jetpack.mvvm.utils.UtilConst.DB_THE_MOVIE_DB
import javax.inject.Singleton

/**
 * Created by wahyu on 28/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_THE_MOVIE_DB).build()
    }

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao()
    }

    @Provides
    fun provideTvShowDao(appDatabase: AppDatabase): TvShowDao {
        return appDatabase.tvShowDao()
    }
}