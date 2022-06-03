package skycode.dicoding.jetpack.mvvm.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import org.jetbrains.annotations.NotNull

/**
 * Created by wahyu on 28/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

@Entity(tableName = "tbl_movie", primaryKeys = ["id"])
data class MovieEntity(

    @NotNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "info_genre")
    val infoGenre: String,
)