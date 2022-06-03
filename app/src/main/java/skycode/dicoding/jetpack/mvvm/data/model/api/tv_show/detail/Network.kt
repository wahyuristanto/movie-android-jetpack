package skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.detail


import com.google.gson.annotations.SerializedName

data class Network(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("logo_path")
    var logoPath: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("origin_country")
    var originCountry: String? = null
)