package skycode.dicoding.jetpack.mvvm.ui.tv_show

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
import skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.TvShowResponse
import skycode.dicoding.jetpack.mvvm.data.model.offline.MovieDummy
import skycode.dicoding.jetpack.mvvm.usecase.DataUseCase

/**
 * Created by wahyu on 29/05/21
 * Find me on my Github -> https://github.com/wahyuristanto
 */

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: DataUseCase

    @Mock
    private lateinit var observerTvShowResponse: Observer<TvShowResponse>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(useCase)
    }

    @Test
    fun testGetTvShows() {
        val tvShowDummy = MovieDummy.getDummyTvShow()
        val tvShowResponse = MutableLiveData<TvShowResponse>()
        tvShowResponse.value = tvShowDummy

        `when`(useCase.getTvShows()).thenReturn(tvShowResponse)
        val tvShows = viewModel.getTvShows().value
        verify(useCase).getTvShows()
        assertNotNull(tvShows)
        assertEquals(tvShowDummy.results?.size, tvShows?.results?.size)

        viewModel.getTvShows().observeForever(observerTvShowResponse)
        verify(observerTvShowResponse).onChanged(tvShowDummy)
    }
}