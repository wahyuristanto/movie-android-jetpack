package skycode.dicoding.jetpack.mvvm.utils

import androidx.test.espresso.idling.CountingIdlingResource

/**
 * Created by wahyu on 25/06/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        idlingResource.increment()
    }

    fun decrement() {
        idlingResource.decrement()
    }
}