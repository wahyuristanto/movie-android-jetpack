package skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.detail


import com.google.gson.annotations.SerializedName

data class ProductionCountryTv(
    @SerializedName("iso_3166_1")
    var iso31661: String? = null,
    @SerializedName("name")
    var name: String? = null
)