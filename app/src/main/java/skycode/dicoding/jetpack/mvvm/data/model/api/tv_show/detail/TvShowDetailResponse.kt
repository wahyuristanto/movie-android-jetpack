package skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.detail


import com.google.gson.annotations.SerializedName

data class TvShowDetailResponse(
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,
    @SerializedName("created_by")
    var createdBy: List<CreatedBy>? = null,
    @SerializedName("episode_run_time")
    var episodeRunTime: List<Int>? = null,
    @SerializedName("first_air_date")
    var firstAirDate: String? = null,
    @SerializedName("genres")
    var genres: List<GenreTv>? = null,
    @SerializedName("homepage")
    var homepage: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("in_production")
    var inProduction: Boolean? = null,
    @SerializedName("languages")
    var languages: List<String>? = null,
    @SerializedName("last_air_date")
    var lastAirDate: String? = null,
    @SerializedName("last_episode_to_air")
    var lastEpisodeToAir: LastEpisodeToAir? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("networks")
    var networks: List<Network>? = null,
    @SerializedName("next_episode_to_air")
    var nextEpisodeToAir: Any? = null,
    @SerializedName("number_of_episodes")
    var numberOfEpisodes: Int? = null,
    @SerializedName("number_of_seasons")
    var numberOfSeasons: Int? = null,
    @SerializedName("origin_country")
    var originCountry: List<String>? = null,
    @SerializedName("original_language")
    var originalLanguage: String? = null,
    @SerializedName("original_name")
    var originalName: String? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("popularity")
    var popularity: Double? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("production_companies")
    var productionCompanies: List<ProductionCompany>? = null,
    @SerializedName("production_countries")
    var productionCountries: List<ProductionCountryTv>? = null,
    @SerializedName("seasons")
    var seasons: List<Season>? = null,
    @SerializedName("spoken_languages")
    var spokenLanguages: List<SpokenLanguage>? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("tagline")
    var tagline: String? = null,
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("vote_average")
    var voteAverage: Double? = null,
    @SerializedName("vote_count")
    var voteCount: Int? = null
)