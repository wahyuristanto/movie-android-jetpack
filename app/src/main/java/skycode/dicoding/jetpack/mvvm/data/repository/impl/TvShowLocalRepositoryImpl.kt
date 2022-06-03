package skycode.dicoding.jetpack.mvvm.data.repository.impl

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import skycode.dicoding.jetpack.mvvm.data.local.db.dao.TvShowDao
import skycode.dicoding.jetpack.mvvm.data.model.db.TvShowEntity
import skycode.dicoding.jetpack.mvvm.data.repository.TvShowLocalRepository
import javax.inject.Inject

/**
 * Created by wahyu on 28/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

class TvShowLocalRepositoryImpl @Inject constructor(
    private val tvShowDao: TvShowDao
) : TvShowLocalRepository {

    override suspend fun insertTvShow(tvShow: TvShowEntity) = tvShowDao.insertTvShow(tvShow)
    override suspend fun deleteTvShow(tvShow: TvShowEntity) = tvShowDao.deleteTvShow(tvShow)
    override fun loadTvShow(): DataSource.Factory<Int, TvShowEntity> = tvShowDao.getTvShows()
    override fun getTvShowById(tvShowId: Int): LiveData<TvShowEntity> = tvShowDao.getMovieById(tvShowId)
}