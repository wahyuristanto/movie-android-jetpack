package skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.detail


import com.google.gson.annotations.SerializedName

data class CreatedBy(
    @SerializedName("credit_id")
    var creditId: String? = null,
    @SerializedName("gender")
    var gender: Int? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("profile_path")
    var profilePath: String? = null
)