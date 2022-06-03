package skycode.dicoding.jetpack.mvvm.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import skycode.dicoding.jetpack.mvvm.data.model.api.movie.detail.MovieDetailResponse
import skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.detail.TvShowDetailResponse
import skycode.dicoding.jetpack.mvvm.data.model.offline.MovieDummy
import skycode.dicoding.jetpack.mvvm.usecase.DataUseCase

/**
 * Created by wahyu on 29/05/21
 * Find me on my Github -> https://github.com/wahyuristanto
 */

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val movieDetailDummy = MovieDummy.getDummyMovieDetail()
    private val tvShowDetailDummy = MovieDummy.getDummyTvShowDetail()
    private val movieIdDummy = movieDetailDummy.id ?: 0
    private val tvShowIdDummy = tvShowDetailDummy.id ?: 0

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: DataUseCase

    @Mock
    private lateinit var observerMovieDetailResponse: Observer<MovieDetailResponse>

    @Mock
    private lateinit var observerTvShowDetailResponse: Observer<TvShowDetailResponse>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(useCase)
    }

    @Test
    fun testGetDetailMovie() {
        val movieDetailResponse = MutableLiveData<MovieDetailResponse>()
        movieDetailResponse.value = movieDetailDummy

        `when`(useCase.getMovieDetail(movieIdDummy)).thenReturn(movieDetailResponse)
        val movieDetail = viewModel.getDetailMovieById(movieIdDummy).value
        verify(useCase).getMovieDetail(movieIdDummy)

        assertNotNull(movieDetail)
        assertEquals(movieDetailDummy.id, movieDetail?.id)
        assertEquals(movieDetailDummy, movieDetail)

        viewModel.getDetailMovieById(movieDetail?.id ?: 0).observeForever(observerMovieDetailResponse)
        verify(observerMovieDetailResponse).onChanged(movieDetailDummy)
    }

    @Test
    fun testGetDetailTvShow() {
        val tvShowDetailResponse = MutableLiveData<TvShowDetailResponse>()
        tvShowDetailResponse.value = tvShowDetailDummy

        `when`(useCase.getTvShowDetail(tvShowIdDummy)).thenReturn(tvShowDetailResponse)
        val tvShowDetail = viewModel.getDetailTvShowById(tvShowIdDummy).value
        verify(useCase).getTvShowDetail(tvShowIdDummy)

        assertNotNull(tvShowDetail)
        assertEquals(tvShowDetailDummy.id, tvShowDetail?.id)
        assertEquals(tvShowDetailDummy, tvShowDetail)

        viewModel.getDetailTvShowById(tvShowDetail?.id ?: 0).observeForever(observerTvShowDetailResponse)
        verify(observerTvShowDetailResponse).onChanged(tvShowDetailDummy)
    }
}