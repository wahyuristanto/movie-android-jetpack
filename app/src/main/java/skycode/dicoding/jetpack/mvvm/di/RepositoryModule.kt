package skycode.dicoding.jetpack.mvvm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import skycode.dicoding.jetpack.mvvm.data.local.db.dao.MovieDao
import skycode.dicoding.jetpack.mvvm.data.local.db.dao.TvShowDao
import skycode.dicoding.jetpack.mvvm.data.remote.ApiService
import skycode.dicoding.jetpack.mvvm.data.repository.impl.MovieLocalRepositoryImpl
import skycode.dicoding.jetpack.mvvm.data.repository.impl.MovieRepositoryImpl
import skycode.dicoding.jetpack.mvvm.data.repository.impl.TvShowLocalRepositoryImpl
import skycode.dicoding.jetpack.mvvm.data.repository.impl.TvShowRepositoryImpl

/**
 * Created by wahyu on 03/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    fun provideMovieRepositoryImpl(apiService: ApiService) = MovieRepositoryImpl(apiService)

    @Provides
    fun provideTvShowRepositoryImpl(apiService: ApiService) = TvShowRepositoryImpl(apiService)

    @Provides
    fun provideMovieLocalRepositoryImpl(movieDao: MovieDao) = MovieLocalRepositoryImpl(movieDao)

    @Provides
    fun provideTvShowLocalRepositoryImpl(tvShowDao: TvShowDao) = TvShowLocalRepositoryImpl(tvShowDao)
}