package skycode.dicoding.jetpack.mvvm.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import skycode.dicoding.jetpack.mvvm.data.model.db.TvShowEntity

/**
 * Created by wahyu on 28/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

interface TvShowLocalRepository {
    suspend fun insertTvShow(tvShow: TvShowEntity)
    suspend fun deleteTvShow(tvShow: TvShowEntity)
    fun loadTvShow(): DataSource.Factory<Int, TvShowEntity>
    fun getTvShowById(tvShowId: Int): LiveData<TvShowEntity>
}