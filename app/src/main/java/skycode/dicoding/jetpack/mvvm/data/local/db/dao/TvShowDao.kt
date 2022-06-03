package skycode.dicoding.jetpack.mvvm.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import skycode.dicoding.jetpack.mvvm.data.model.db.TvShowEntity

/**
 * Created by wahyu on 28/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM tbl_tv_show")
    fun getTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tbl_tv_show WHERE id = :tvShowId")
    fun getMovieById(tvShowId: Int): LiveData<TvShowEntity>

    @Delete
    suspend fun deleteTvShow(tvShow: TvShowEntity)
}