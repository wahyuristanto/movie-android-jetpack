package skycode.dicoding.jetpack.mvvm.data.repository

import skycode.dicoding.jetpack.mvvm.data.repository.impl.MovieRepositoryImpl

/**
 * Created by wahyu on 03/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

interface MovieRepository {
    fun getMovies(callback: MovieRepositoryImpl.OnGetMovieCallback)
    fun getMovieDetail(movieId: Int, callback: MovieRepositoryImpl.OnGetMovieDetailCallback)
}