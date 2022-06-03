package skycode.dicoding.jetpack.mvvm.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import skycode.dicoding.jetpack.mvvm.data.model.db.MovieEntity
import skycode.dicoding.jetpack.mvvm.usecase.DataUseCase

/**
 * Created by wahyu on 29/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

@RunWith(MockitoJUnitRunner::class)
class MovieFavViewModelTest {

    private lateinit var viewModel: MovieFavViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: DataUseCase

    @Mock
    private lateinit var observerMovies: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedListMovies: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieFavViewModel(useCase)
    }

    @Test
    fun testGetMovies() {
        val movieDummy = pagedListMovies
        Mockito.`when`(movieDummy.size).thenReturn(5)
        val moviesPaged = MutableLiveData<PagedList<MovieEntity>>()
        moviesPaged.value = movieDummy

        Mockito.`when`(useCase.loadMovie()).thenReturn(moviesPaged)
        val movies = viewModel.loadMovie().value
        verify(useCase).loadMovie()
        assertNotNull(movies)
        assertEquals(movieDummy.size, movies?.size)

        viewModel.loadMovie().observeForever(observerMovies)
        verify(observerMovies).onChanged(movieDummy)
    }
}