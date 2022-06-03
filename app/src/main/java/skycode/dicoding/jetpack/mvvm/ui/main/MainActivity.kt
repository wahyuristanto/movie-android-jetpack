package skycode.dicoding.jetpack.mvvm.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import skycode.dicoding.jetpack.mvvm.R
import skycode.dicoding.jetpack.mvvm.databinding.ActivityMainBinding
import skycode.dicoding.jetpack.mvvm.ui.favorite.FavoriteActivity
import skycode.dicoding.jetpack.mvvm.ui.movie.MovieFragment
import skycode.dicoding.jetpack.mvvm.ui.tv_show.TvShowFragment
import skycode.dicoding.jetpack.mvvm.utils.UtilExtensions.openActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPrepare()
        initView()
    }

    private fun initPrepare() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initView() {
        val titles = arrayListOf(getString(R.string.movies), getString(R.string.tv_show))
        val fragments = arrayListOf(MovieFragment(), TvShowFragment())
        val viewPagerAdapter = ViewPagerAdapter(titles, fragments, supportFragmentManager)

        binding.viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favoriteItem -> {
                openActivity(FavoriteActivity::class.java)
                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }
}