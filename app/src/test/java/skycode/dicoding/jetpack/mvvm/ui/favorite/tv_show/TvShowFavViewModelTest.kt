package skycode.dicoding.jetpack.mvvm.ui.favorite.tv_show

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
import skycode.dicoding.jetpack.mvvm.data.model.db.TvShowEntity
import skycode.dicoding.jetpack.mvvm.usecase.DataUseCase

/**
 * Created by wahyu on 29/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

@RunWith(MockitoJUnitRunner::class)
class TvShowFavViewModelTest {

    private lateinit var viewModel: TvShowFavViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: DataUseCase

    @Mock
    private lateinit var observerTvShows: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedListTvShows: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowFavViewModel(useCase)
    }

    @Test
    fun testGetTvShows() {
        val tvShowDummy = pagedListTvShows
        Mockito.`when`(tvShowDummy.size).thenReturn(5)
        val tvShowsPaged = MutableLiveData<PagedList<TvShowEntity>>()
        tvShowsPaged.value = tvShowDummy

        Mockito.`when`(useCase.loadTvShow()).thenReturn(tvShowsPaged)
        val tvShows = viewModel.loadTvShow().value
        verify(useCase).loadTvShow()
        assertNotNull(tvShows)
        assertEquals(tvShowDummy.size, tvShows?.size)

        viewModel.loadTvShow().observeForever(observerTvShows)
        verify(observerTvShows).onChanged(tvShowDummy)
    }
}