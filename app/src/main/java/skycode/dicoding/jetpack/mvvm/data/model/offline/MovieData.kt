package skycode.dicoding.jetpack.mvvm.data.model.offline

/**
 * Created by wahyu on 28/05/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

data class MovieData(
    var movieId: String,
    var title: String,
    var imgPath: Int,
    var rate: String,
    var date: String,
    var genre: String,
    var desc: String,
    var duration: String,
    var country: String
)