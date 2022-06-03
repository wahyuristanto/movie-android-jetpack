package skycode.dicoding.jetpack.mvvm.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import skycode.dicoding.jetpack.mvvm.data.model.api.movie.ResultMovie
import skycode.dicoding.jetpack.mvvm.databinding.FragmentMovieBinding
import skycode.dicoding.jetpack.mvvm.ui.detail.DetailMovieActivity
import skycode.dicoding.jetpack.mvvm.utils.UtilExtensions.isVisible
import skycode.dicoding.jetpack.mvvm.utils.UtilExtensions.openActivity
import skycode.dicoding.jetpack.mvvm.utils.UtilFunctions.loge

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel>()
    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter { item -> setDataMovie(item) }
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
        viewModel.getMovies().observe(viewLifecycleOwner, {
            binding.noDataTV.isVisible(it.results?.size ?: 0 <= 0)
            movieAdapter.setMovies(it.results)
            with(binding.movieListRV) {
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        })

        viewModel.getFailureMessage().observe(viewLifecycleOwner, {
            loge(it.message.toString())
        })

        viewModel.getIsLoadData().observe(viewLifecycleOwner, {
            binding.loadingSKV.isVisible(it)
        })
    }

    private fun setDataMovie(item: ResultMovie) {
        context?.openActivity(DetailMovieActivity::class.java) {
            putInt(DetailMovieActivity.EXTRA_ID_MOVIE, item.id ?: 0)
            putString(DetailMovieActivity.EXTRA_TAG, TAG)
        }
    }

    companion object {
        val TAG = MovieFragment::class.java.name ?: ""
    }
}