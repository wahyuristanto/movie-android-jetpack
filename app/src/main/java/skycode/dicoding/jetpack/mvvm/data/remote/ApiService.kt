package skycode.dicoding.jetpack.mvvm.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import skycode.dicoding.jetpack.mvvm.data.model.api.movie.MovieResponse
import skycode.dicoding.jetpack.mvvm.data.model.api.movie.detail.MovieDetailResponse
import skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.TvShowResponse
import skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.detail.TvShowDetailResponse

/**
 * Created by wahyu on 03/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

interface ApiService {
    @GET("discover/movie")
    fun getMovies(): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Int): Call<MovieDetailResponse>

    @GET("discover/tv")
    fun getTvShows(): Call<TvShowResponse>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(@Path("tv_id") tvShowId: Int): Call<TvShowDetailResponse>
}