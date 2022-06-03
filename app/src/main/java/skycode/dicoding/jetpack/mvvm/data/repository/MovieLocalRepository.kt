package skycode.dicoding.jetpack.mvvm.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import skycode.dicoding.jetpack.mvvm.data.model.db.MovieEntity

/**
 * Created by wahyu on 28/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

interface MovieLocalRepository {
    suspend fun insertMovie(movie: MovieEntity)
    suspend fun deleteMovie(movie: MovieEntity)
    fun loadMovie(): DataSource.Factory<Int, MovieEntity>
    fun getMovieById(movieId: Int): LiveData<MovieEntity>
}