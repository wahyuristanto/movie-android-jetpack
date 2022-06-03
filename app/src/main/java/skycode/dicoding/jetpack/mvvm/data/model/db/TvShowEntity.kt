package skycode.dicoding.jetpack.mvvm.data.model.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * Created by wahyu on 28/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

@Entity(tableName = "tbl_tv_show", primaryKeys = ["id"])
data class TvShowEntity(

    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double,

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "info_genre")
    val infoGenre: String,
)