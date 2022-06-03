package skycode.dicoding.jetpack.mvvm.data.repository.impl

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import skycode.dicoding.jetpack.mvvm.data.model.api.movie.MovieResponse
import skycode.dicoding.jetpack.mvvm.data.model.api.movie.detail.MovieDetailResponse
import skycode.dicoding.jetpack.mvvm.data.remote.ApiService
import skycode.dicoding.jetpack.mvvm.data.repository.MovieRepository
import skycode.dicoding.jetpack.mvvm.utils.EspressoIdlingResource
import javax.inject.Inject

/**
 * Created by wahyu on 03/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {

    override fun getMovies(callback: OnGetMovieCallback) {
        EspressoIdlingResource.increment()
        apiService.getMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                callback.onGetMovie(response.body() ?: return)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                callback.onFailure(t)
                EspressoIdlingResource.decrement()
            }
        })
    }

    override fun getMovieDetail(movieId: Int, callback: OnGetMovieDetailCallback) {
        EspressoIdlingResource.increment()
        apiService.getMovieDetail(movieId).enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                callback.onGetMovieDetail(response.body() ?: return)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                callback.onFailure(t)
                EspressoIdlingResource.decrement()
            }
        })
    }

    interface OnGetMovieCallback {
        fun onGetMovie(movie: MovieResponse)
        fun onFailure(throwable: Throwable)
    }

    interface OnGetMovieDetailCallback {
        fun onGetMovieDetail(movieDetail: MovieDetailResponse)
        fun onFailure(throwable: Throwable)
    }
}