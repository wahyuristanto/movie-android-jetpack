package skycode.dicoding.jetpack.mvvm.ui.favorite.tv_show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import skycode.dicoding.jetpack.mvvm.data.model.db.TvShowEntity
import skycode.dicoding.jetpack.mvvm.databinding.FragmentTvShowBinding
import skycode.dicoding.jetpack.mvvm.ui.detail.DetailMovieActivity
import skycode.dicoding.jetpack.mvvm.ui.tv_show.TvShowFragment
import skycode.dicoding.jetpack.mvvm.utils.UtilExtensions.isVisible
import skycode.dicoding.jetpack.mvvm.utils.UtilExtensions.openActivity

@AndroidEntryPoint
class TvShowFavFragment : Fragment() {
    private lateinit var binding: FragmentTvShowBinding
    private val viewModel by viewModels<TvShowFavViewModel>()
    private val tvShowFavAdapter: TvShowFavAdapter by lazy {
        TvShowFavAdapter { item -> setDataMovie(item) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity ?: return
        initObserver()
    }

    private fun initObserver() {
        viewModel.loadTvShow().observe(viewLifecycleOwner, {
            binding.loadingSKV.isVisible(false)
            binding.noDataTV.isVisible(it.size <= 0)
            tvShowFavAdapter.submitList(it)
            with(binding.tvListRV) {
                setHasFixedSize(true)
                adapter = tvShowFavAdapter
            }
        })
    }

    private fun setDataMovie(item: TvShowEntity) {
        context?.openActivity(DetailMovieActivity::class.java) {
            putInt(DetailMovieActivity.EXTRA_ID_MOVIE, item.id)
            putString(DetailMovieActivity.EXTRA_TAG, TAG)
        }
    }

    companion object {
        val TAG = TvShowFragment::class.java.simpleName ?: ""
    }
}