package skycode.dicoding.jetpack.mvvm.ui.movie

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
import skycode.dicoding.jetpack.mvvm.data.model.api.movie.MovieResponse
import skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.TvShowResponse
import skycode.dicoding.jetpack.mvvm.data.model.offline.MovieDummy
import skycode.dicoding.jetpack.mvvm.usecase.DataUseCase

/**
 * Created by wahyu on 29/05/21
 * Find me on my Github -> https://github.com/wahyuristanto
 */

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: DataUseCase

    @Mock
    private lateinit var observerMovieResponse: Observer<MovieResponse>

    @Mock
    private lateinit var observerTvShowResponse: Observer<TvShowResponse>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(useCase)
    }

    @Test
    fun testGetMovies() {
        val movieDummy = MovieDummy.getDummyMovie()
        val movieResponse = MutableLiveData<MovieResponse>()
        movieResponse.value = movieDummy

        `when`(useCase.getMovies()).thenReturn(movieResponse)
        val movies = viewModel.getMovies().value
        verify(useCase).getMovies()
        assertNotNull(movies)
        assertEquals(movieDummy.results?.size, movies?.results?.size)

        viewModel.getMovies().observeForever(observerMovieResponse)
        verify(observerMovieResponse).onChanged(movieDummy)
    }
}