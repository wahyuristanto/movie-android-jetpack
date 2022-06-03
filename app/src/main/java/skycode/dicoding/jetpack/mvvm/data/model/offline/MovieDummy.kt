package skycode.dicoding.jetpack.mvvm.data.model.offline

import skycode.dicoding.jetpack.mvvm.data.model.api.movie.MovieResponse
import skycode.dicoding.jetpack.mvvm.data.model.api.movie.ResultMovie
import skycode.dicoding.jetpack.mvvm.data.model.api.movie.detail.Genre
import skycode.dicoding.jetpack.mvvm.data.model.api.movie.detail.MovieDetailResponse
import skycode.dicoding.jetpack.mvvm.data.model.api.movie.detail.ProductionCountry
import skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.ResultTv
import skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.TvShowResponse
import skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.detail.GenreTv
import skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.detail.ProductionCountryTv
import skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.detail.TvShowDetailResponse
import skycode.dicoding.jetpack.mvvm.data.model.db.MovieEntity
import skycode.dicoding.jetpack.mvvm.data.model.db.TvShowEntity

/**
 * Created by wahyu on 28/05/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

object MovieDummy {

    /** API **/
    fun getDummyMovie(): MovieResponse {
        val movies = mutableListOf<ResultMovie>()
        val movieResponse = MovieResponse(results = movies)
        movies.add(
            ResultMovie(
                false,
                "/620hnMVLu6RSZW6a5rwO8gqpt0t.jpg",
                null,
                508943,
                "en",
                "Luca",
                "Luca and his best friend Alberto experience an unforgettable summer on the Italian Riviera. But all the fun is threatened by a deeply-held secret: they are sea monsters from another world just below the water’s surface.",
                6852.159,
                "/7rhzEufovmmUqVjcbzMHTBQ2SCG.jpg",
                "2021-06-17",
                "Luca",
                false,
                8.2,
                1147
            )
        )
        return movieResponse
    }

    fun getDummyMovieDetail(): MovieDetailResponse {
        val genres = mutableListOf<Genre>()
        genres.add(Genre(16, "Animation"))
        genres.add(Genre(35, "Comedy"))
        genres.add(Genre(10751, "Family"))
        genres.add(Genre(14, "Fantasy"))

        val countries = mutableListOf<ProductionCountry>()
        countries.add(ProductionCountry("US", "United States of America"))

        return MovieDetailResponse(
            false,
            "/620hnMVLu6RSZW6a5rwO8gqpt0t.jpg",
            null,
            0,
            genres,
            "https://www.disneyplus.com/movies/luca/7K1HyQ6Hl16P",
            508943,
            "tt12801262",
            "en",
            "Luca",
            "Luca and his best friend Alberto experience an unforgettable summer on the Italian Riviera. But all the fun is threatened by a deeply-held secret: they are sea monsters from another world just below the water’s surface.",
            6852.159,
            "/7rhzEufovmmUqVjcbzMHTBQ2SCG.jpg",
            null,
            countries,
            "2021-06-17",
            5000000,
            95,
            null,
            "Released",
            "",
            "Luca",
            false,
            8.2,
            1199
        )
    }

    fun getDummyTvShow(): TvShowResponse {
        val tvShows = mutableListOf<ResultTv>()
        val tvShowResponse = TvShowResponse(results = tvShows)
        tvShows.add(
            ResultTv(
                "/wr7nrzDrpGCEgYnw15jyAB59PtZ.jpg",
                "2021-06-09",
                null,
                84958,
                "Loki",
                null,
                "en",
                "Loki",
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                6582.117,
                "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                8.1,
                3936
            )
        )
        return tvShowResponse
    }

    fun getDummyTvShowDetail(): TvShowDetailResponse {
        val genres = mutableListOf<GenreTv>()
        genres.add(GenreTv(18, "Drama"))
        genres.add(GenreTv(10765, "Sci-Fi & Fantasy"))

        val countries = mutableListOf<ProductionCountryTv>()
        countries.add(ProductionCountryTv("US", "United States of America"))

        return TvShowDetailResponse(
            "/wr7nrzDrpGCEgYnw15jyAB59PtZ.jpg",
            null,
            null,
            "2021-06-09",
            genres,
            "https://www.disneyplus.com/series/wp/6pARMvILBGzF",
            84958,
            true,
            null,
            "2021-06-23",
            null,
            "Loki",
            null,
            null,
            6,
            1,
            null,
            "en",
            "Loki",
            "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
            6582.117,
            "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
            null,
            countries,
            null,
            null,
            "Returning Series",
            "Loki's time has come.",
            "Miniseries",
            8.1,
            3984
        )
    }

    /** Local **/

    fun getDummyMovieFav(): MutableList<MovieEntity> {
        val movies = mutableListOf<MovieEntity>()
        movies.add(
            MovieEntity(
                508943,
                "Luca",
                "2021-06-17",
                8.2,
                "/620hnMVLu6RSZW6a5rwO8gqpt0t.jpg",
                "Luca and his best friend Alberto experience an unforgettable summer on the Italian Riviera. But all the fun is threatened by a deeply-held secret: they are sea monsters from another world just below the water’s surface.",
                "2021-06-17 • US • Animation, Comedy, Family, Fantasy • 95 Minutes",
            )
        )
        return movies
    }

    fun getDummyTvShowFav(): MutableList<TvShowEntity> {
        val movies = mutableListOf<TvShowEntity>()
        movies.add(
            TvShowEntity(
                84958,
                "Loki",
                "2021-06-09",
                8.1,
                "/wr7nrzDrpGCEgYnw15jyAB59PtZ.jpg",
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                "2021-06-09 • US • Drama, Sci-Fi & Fantasy • 8 Minutes",
            )
        )
        return movies
    }
}