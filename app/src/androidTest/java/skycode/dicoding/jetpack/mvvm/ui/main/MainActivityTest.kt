package skycode.dicoding.jetpack.mvvm.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import skycode.dicoding.jetpack.mvvm.R
import skycode.dicoding.jetpack.mvvm.data.remote.ApiService
import skycode.dicoding.jetpack.mvvm.utils.EspressoIdlingResource
import skycode.dicoding.jetpack.mvvm.utils.UtilConst.SAMPLE_MOVIE_ID
import skycode.dicoding.jetpack.mvvm.utils.UtilConst.SAMPLE_POSITION
import skycode.dicoding.jetpack.mvvm.utils.UtilConst.SAMPLE_TV_SHOW_ID
import skycode.dicoding.jetpack.mvvm.utils.UtilConst.STR_FAV_MOVIE
import skycode.dicoding.jetpack.mvvm.utils.UtilConst.STR_TV_SHOW
import skycode.dicoding.jetpack.mvvm.utils.UtilFunctions.subStringComma
import javax.inject.Inject

/**
 * Created by wahyu on 29/05/21
 * Find me on my Github -> https://github.com/wahyuristanto
 */

@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Inject
    lateinit var apiService: ApiService

    @Before
    fun setUp() {
        hiltRule.inject()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        val movies = apiService.getMovies().execute().body()?.results
        onView(withId(R.id.movieListRV)).check(matches(isDisplayed()))
        onView(withId(R.id.movieListRV)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movies?.size ?: 0))
    }

    @Test
    fun loadDetailMovie() {
        val movie = apiService.getMovieDetail(SAMPLE_MOVIE_ID).execute().body()
        val strCompanies = arrayListOf<String>()
        val strGenres = arrayListOf<String>()
        val strRunTime = "${movie?.runtime} Minutes"
        for (i in (movie?.productionCountries?.indices ?: return)) strCompanies.add(movie.productionCountries?.get(i)?.iso31661.toString())
        for (i in (movie.genres?.indices ?: return)) strGenres.add(movie.genres?.get(i)?.name.toString())
        val strInfo = "${movie.releaseDate} • ${strCompanies.subStringComma()} • ${strGenres.subStringComma()} • $strRunTime"

        onView(withId(R.id.movieListRV)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(SAMPLE_POSITION, click()))
        onView(withId(R.id.titleTV)).check(matches(isDisplayed()))
        onView(withId(R.id.titleTV)).check(matches(withText(movie.title)))
        onView(withId(R.id.descriptionTV)).check(matches(isDisplayed()))
        onView(withId(R.id.descriptionTV)).check(matches(withText(movie.overview)))
        onView(withId(R.id.movieRateTV)).check(matches(isDisplayed()))
        onView(withId(R.id.movieRateTV)).check(matches(withText(movie.voteAverage.toString())))
        onView(withId(R.id.infoGenreTV)).check(matches(isDisplayed()))
        onView(withId(R.id.infoGenreTV)).check(matches(withText(strInfo)))
    }

    @Test
    fun loadTvShows() {
        val tvShows = apiService.getTvShows().execute().body()?.results
        onView(withText(STR_TV_SHOW)).perform(click())
        onView(withId(R.id.tvListRV)).check(matches(isDisplayed()))
        onView(withId(R.id.tvListRV)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvShows?.size ?: 0))
    }

    @Test
    fun loadDetailTvShow() {
        val tvShow = apiService.getTvShowDetail(SAMPLE_TV_SHOW_ID).execute().body()
        val strCompanies = arrayListOf<String>()
        val strGenres = arrayListOf<String>()
        val strRunTime = "${tvShow?.numberOfEpisodes.toString()} Episodes"
        for (i in (tvShow?.productionCountries?.indices ?: return)) strCompanies.add(tvShow.productionCountries?.get(i)?.iso31661.toString())
        for (i in (tvShow.genres?.indices ?: return)) strGenres.add(tvShow.genres?.get(i)?.name.toString())
        val strInfo = "${tvShow.firstAirDate} • ${strCompanies.subStringComma()} • ${strGenres.subStringComma()} • $strRunTime"

        onView(withText(STR_TV_SHOW)).perform(click())
        onView(withId(R.id.tvListRV)).check(matches(isDisplayed()))
        onView(withId(R.id.tvListRV)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(SAMPLE_POSITION, click()))
        onView(withId(R.id.titleTV)).check(matches(isDisplayed()))
        onView(withId(R.id.titleTV)).check(matches(withText(tvShow.name)))
        onView(withId(R.id.descriptionTV)).check(matches(isDisplayed()))
        onView(withId(R.id.descriptionTV)).check(matches(withText(tvShow.overview)))
        onView(withId(R.id.movieRateTV)).check(matches(isDisplayed()))
        onView(withId(R.id.movieRateTV)).check(matches(withText(tvShow.voteAverage.toString())))
        onView(withId(R.id.infoGenreTV)).check(matches(isDisplayed()))
        onView(withId(R.id.infoGenreTV)).check(matches(withText(strInfo)))
    }

    @Test
    fun loadMoviesFav() {
        onView(withId(R.id.movieListRV)).check(matches(isDisplayed()))
        onView(withId(R.id.movieListRV)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(SAMPLE_POSITION, click()))
        onView(withId(R.id.favoriteFAB)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText(STR_FAV_MOVIE)).perform(click())
        onView(withId(R.id.movieListRV)).check(matches(isDisplayed()))
        onView(withId(R.id.movieListRV)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(SAMPLE_POSITION, click()))
        onView(withId(R.id.unFavoriteFAB)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadTvShowFav() {
        onView(withText(STR_TV_SHOW)).perform(click())
        onView(withId(R.id.tvListRV)).check(matches(isDisplayed()))
        onView(withId(R.id.tvListRV)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(SAMPLE_POSITION, click()))
        onView(withId(R.id.favoriteFAB)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText(STR_FAV_MOVIE)).perform(click())
        onView(withText(STR_TV_SHOW)).perform(click())
        onView(withId(R.id.tvListRV)).check(matches(isDisplayed()))
        onView(withId(R.id.tvListRV)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(SAMPLE_POSITION, click()))
        onView(withId(R.id.unFavoriteFAB)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }
}