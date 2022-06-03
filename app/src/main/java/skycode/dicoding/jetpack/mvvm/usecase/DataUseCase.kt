package skycode.dicoding.jetpack.mvvm.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import skycode.dicoding.jetpack.mvvm.data.model.api.movie.MovieResponse
import skycode.dicoding.jetpack.mvvm.data.model.api.movie.detail.MovieDetailResponse
import skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.TvShowResponse
import skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.detail.TvShowDetailResponse
import skycode.dicoding.jetpack.mvvm.data.model.db.MovieEntity
import skycode.dicoding.jetpack.mvvm.data.model.db.TvShowEntity
import skycode.dicoding.jetpack.mvvm.data.repository.impl.MovieLocalRepositoryImpl
import skycode.dicoding.jetpack.mvvm.data.repository.impl.MovieRepositoryImpl
import skycode.dicoding.jetpack.mvvm.data.repository.impl.TvShowLocalRepositoryImpl
import skycode.dicoding.jetpack.mvvm.data.repository.impl.TvShowRepositoryImpl
import javax.inject.Inject

/**
 * Created by wahyu on 03/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

class DataUseCase @Inject constructor(
    private val movieRepositoryImpl: MovieRepositoryImpl,
    private val tvShowRepositoryImpl: TvShowRepositoryImpl,
    private val movieLocalRepositoryImpl: MovieLocalRepositoryImpl,
    private val tvShowLocalRepositoryImpl: TvShowLocalRepositoryImpl
) {
    val failureMessage = MutableLiveData<Throwable>()
    val onIsLoadData = MutableLiveData<Boolean>()

    fun getMovies(): MutableLiveData<MovieResponse> {
        onIsLoadData.postValue(true)
        val movieLiveData = MutableLiveData<MovieResponse>()
        movieRepositoryImpl.getMovies(object : MovieRepositoryImpl.OnGetMovieCallback {
            override fun onGetMovie(movie: MovieResponse) {
                movieLiveData.postValue(movie)
                onIsLoadData.postValue(false)
            }

            override fun onFailure(throwable: Throwable) {
                failureMessage.postValue(throwable)
                onIsLoadData.postValue(false)
            }
        })
        return movieLiveData
    }

    fun getMovieDetail(movieId: Int): MutableLiveData<MovieDetailResponse> {
        onIsLoadData.postValue(true)
        val movieDetailLiveData = MutableLiveData<MovieDetailResponse>()
        movieRepositoryImpl.getMovieDetail(movieId, object : MovieRepositoryImpl.OnGetMovieDetailCallback {
            override fun onGetMovieDetail(movieDetail: MovieDetailResponse) {
                movieDetailLiveData.postValue(movieDetail)
                onIsLoadData.postValue(false)
            }

            override fun onFailure(throwable: Throwable) {
                failureMessage.postValue(throwable)
                onIsLoadData.postValue(false)
            }
        })
        return movieDetailLiveData
    }

    fun getTvShows(): MutableLiveData<TvShowResponse> {
        onIsLoadData.postValue(true)
        val tvShowsLiveData = MutableLiveData<TvShowResponse>()
        tvShowRepositoryImpl.getTvShows(object : TvShowRepositoryImpl.OnGetTvShowCallback {
            override fun onGetTvShow(tvShow: TvShowResponse) {
                tvShowsLiveData.postValue(tvShow)
                onIsLoadData.postValue(false)
            }

            override fun onFailure(throwable: Throwable) {
                failureMessage.postValue(throwable)
                onIsLoadData.postValue(false)
            }
        })
        return tvShowsLiveData
    }

    fun getTvShowDetail(tvShowId: Int): MutableLiveData<TvShowDetailResponse> {
        onIsLoadData.postValue(true)
        val tvShowDetailLiveData = MutableLiveData<TvShowDetailResponse>()
        tvShowRepositoryImpl.getTvShowDetail(tvShowId, object : TvShowRepositoryImpl.OnGetTvShowDetailCallback {
            override fun onGetTvShowDetail(tvShowDetail: TvShowDetailResponse) {
                tvShowDetailLiveData.postValue(tvShowDetail)
                onIsLoadData.postValue(false)
            }

            override fun onFailure(throwable: Throwable) {
                failureMessage.postValue(throwable)
                onIsLoadData.postValue(false)
            }
        })
        return tvShowDetailLiveData
    }

    /** Local Storage - Room **/

    suspend fun insertMovie(movie: MovieEntity) = movieLocalRepositoryImpl.insertMovie(movie)
    suspend fun deleteMovie(movie: MovieEntity) = movieLocalRepositoryImpl.deleteMovie(movie)
    fun getMovieById(movieId: Int) = movieLocalRepositoryImpl.getMovieById(movieId)
    fun loadMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(movieLocalRepositoryImpl.loadMovie(), config).build()
    }

    suspend fun insertTvShow(tvShow: TvShowEntity) = tvShowLocalRepositoryImpl.insertTvShow(tvShow)
    suspend fun deleteTvShow(tvShow: TvShowEntity) = tvShowLocalRepositoryImpl.deleteTvShow(tvShow)
    fun getTvShowById(tvShowId: Int) = tvShowLocalRepositoryImpl.getTvShowById(tvShowId)
    fun loadTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(tvShowLocalRepositoryImpl.loadTvShow(), config).build()
    }
}