package skycode.dicoding.jetpack.mvvm.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import skycode.dicoding.jetpack.mvvm.data.model.db.MovieEntity
import skycode.dicoding.jetpack.mvvm.databinding.FragmentMovieBinding
import skycode.dicoding.jetpack.mvvm.ui.detail.DetailMovieActivity
import skycode.dicoding.jetpack.mvvm.ui.movie.MovieFragment
import skycode.dicoding.jetpack.mvvm.utils.UtilExtensions.isVisible
import skycode.dicoding.jetpack.mvvm.utils.UtilExtensions.openActivity

@AndroidEntryPoint
class MovieFavFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieFavViewModel>()
    private val movieAdapter: MovieFavAdapter by lazy {
        MovieFavAdapter { item -> setDataMovie(item) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity ?: return
        initObserver()
    }

    private fun initObserver() {
        viewModel.loadMovie().observe(viewLifecycleOwner, {
            binding.loadingSKV.isVisible(false)
            binding.noDataTV.isVisible(it.size <= 0)
            movieAdapter.submitList(it)
            with(binding.movieListRV) {
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        })
    }

    private fun setDataMovie(item: MovieEntity) {
        context?.openActivity(DetailMovieActivity::class.java) {
            putInt(DetailMovieActivity.EXTRA_ID_MOVIE, item.id)
            putString(DetailMovieActivity.EXTRA_TAG, TAG)
        }
    }

    companion object {
        val TAG = MovieFragment::class.java.name ?: ""
    }
}