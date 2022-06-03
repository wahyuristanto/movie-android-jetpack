package skycode.dicoding.jetpack.mvvm.data.model.api.tv_show


import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("results")
    var results: List<ResultTv>? = null,
    @SerializedName("total_pages")
    var totalPages: Int? = null,
    @SerializedName("total_results")
    var totalResults: Int? = null
)