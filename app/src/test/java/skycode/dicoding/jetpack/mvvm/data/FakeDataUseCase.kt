package skycode.dicoding.jetpack.mvvm.data

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

/**
 * Created by wahyu on 26/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/
class FakeDataUseCase(
    private val movieRepositoryImpl: MovieRepositoryImpl,
    private val tvShowRepositoryImpl: TvShowRepositoryImpl,
    private val movieLocalRepositoryImpl: MovieLocalRepositoryImpl,
    private val tvShowLocalRepositoryImpl: TvShowLocalRepositoryImpl
) {
    val failureMessage = MutableLiveData<Throwable>()
    val onIsLoadData = MutableLiveData<Boolean>()

    fun getMovies(): LiveData<MovieResponse> {
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

    fun getMovieDetail(movieId: Int): LiveData<MovieDetailResponse> {
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

    fun getTvShows(): LiveData<TvShowResponse> {
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

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowDetailResponse> {
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

    fun loadMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(movieLocalRepositoryImpl.loadMovie(), config).build()
    }

    fun loadTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(tvShowLocalRepositoryImpl.loadTvShow(), config).build()
    }
}