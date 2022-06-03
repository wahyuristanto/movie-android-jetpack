package skycode.dicoding.jetpack.mvvm.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import skycode.dicoding.jetpack.mvvm.R
import skycode.dicoding.jetpack.mvvm.databinding.ActivityFavoriteBinding
import skycode.dicoding.jetpack.mvvm.ui.favorite.movie.MovieFavFragment
import skycode.dicoding.jetpack.mvvm.ui.favorite.tv_show.TvShowFavFragment
import skycode.dicoding.jetpack.mvvm.ui.main.ViewPagerAdapter

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPrepare()
        initView()
    }

    private fun initPrepare() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initView() {
        val titles = arrayListOf(getString(R.string.movies), getString(R.string.tv_show))
        val fragments = arrayListOf(MovieFavFragment(), TvShowFavFragment())
        val viewPagerAdapter = ViewPagerAdapter(titles, fragments, supportFragmentManager)

        binding.viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}