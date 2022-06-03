package skycode.dicoding.jetpack.mvvm.data.repository

import skycode.dicoding.jetpack.mvvm.data.repository.impl.TvShowRepositoryImpl

/**
 * Created by wahyu on 03/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

interface TvShowRepository {
    fun getTvShows(callback: TvShowRepositoryImpl.OnGetTvShowCallback)
    fun getTvShowDetail(tvShowId: Int, callback: TvShowRepositoryImpl.OnGetTvShowDetailCallback)
}