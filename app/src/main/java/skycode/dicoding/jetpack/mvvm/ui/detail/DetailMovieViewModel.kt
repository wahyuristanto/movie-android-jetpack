package skycode.dicoding.jetpack.mvvm.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import skycode.dicoding.jetpack.mvvm.data.model.db.MovieEntity
import skycode.dicoding.jetpack.mvvm.data.model.db.TvShowEntity
import skycode.dicoding.jetpack.mvvm.usecase.DataUseCase

/**
 * Created by wahyu on 28/05/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

class DetailMovieViewModel @ViewModelInject constructor(
    private val useCase: DataUseCase
) : ViewModel() {

    /** API **/
    fun getDetailMovieById(movieId: Int) = useCase.getMovieDetail(movieId)
    fun getDetailTvShowById(tvShowId: Int) = useCase.getTvShowDetail(tvShowId)
    fun getFailureMessage() = useCase.failureMessage
    fun getIsLoadData() = useCase.onIsLoadData

    /** Local Storage - Room **/
    suspend fun insertMovie(movie: MovieEntity) {
        viewModelScope.launch {
            useCase.insertMovie(movie)
        }
    }

    suspend fun deleteMovie(movie: MovieEntity) {
        viewModelScope.launch {
            useCase.deleteMovie(movie)
        }
    }

    fun getMovieById(movieId: Int) = useCase.getMovieById(movieId)

    suspend fun insertTvShow(tvShowEntity: TvShowEntity) {
        viewModelScope.launch {
            useCase.insertTvShow(tvShowEntity)
        }
    }

    suspend fun deleteTvShow(tvShowEntity: TvShowEntity) {
        viewModelScope.launch {
            useCase.deleteTvShow(tvShowEntity)
        }
    }

    fun getTvShowById(tvShowId: Int) = useCase.getTvShowById(tvShowId)
}