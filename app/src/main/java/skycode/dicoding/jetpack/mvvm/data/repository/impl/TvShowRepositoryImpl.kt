package skycode.dicoding.jetpack.mvvm.data.repository.impl

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.TvShowResponse
import skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.detail.TvShowDetailResponse
import skycode.dicoding.jetpack.mvvm.data.remote.ApiService
import skycode.dicoding.jetpack.mvvm.data.repository.TvShowRepository
import skycode.dicoding.jetpack.mvvm.utils.EspressoIdlingResource
import javax.inject.Inject

/**
 * Created by wahyu on 03/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

class TvShowRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : TvShowRepository {

    override fun getTvShows(callback: OnGetTvShowCallback) {
        EspressoIdlingResource.increment()
        apiService.getTvShows().enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                callback.onGetTvShow(response.body() ?: return)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                callback.onFailure(t)
                EspressoIdlingResource.decrement()
            }
        })
    }

    override fun getTvShowDetail(tvShowId: Int, callback: OnGetTvShowDetailCallback) {
        EspressoIdlingResource.increment()
        apiService.getTvShowDetail(tvShowId).enqueue(object : Callback<TvShowDetailResponse> {
            override fun onResponse(call: Call<TvShowDetailResponse>, response: Response<TvShowDetailResponse>) {
                callback.onGetTvShowDetail(response.body() ?: return)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                callback.onFailure(t)
                EspressoIdlingResource.decrement()
            }
        })
    }

    interface OnGetTvShowCallback {
        fun onGetTvShow(tvShow: TvShowResponse)
        fun onFailure(throwable: Throwable)
    }

    interface OnGetTvShowDetailCallback {
        fun onGetTvShowDetail(tvShowDetail: TvShowDetailResponse)
        fun onFailure(throwable: Throwable)
    }
}