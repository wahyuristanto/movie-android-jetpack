package skycode.dicoding.jetpack.mvvm.ui.tv_show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.ResultTv
import skycode.dicoding.jetpack.mvvm.databinding.FragmentTvShowBinding
import skycode.dicoding.jetpack.mvvm.ui.detail.DetailMovieActivity
import skycode.dicoding.jetpack.mvvm.utils.UtilExtensions.isVisible
import skycode.dicoding.jetpack.mvvm.utils.UtilExtensions.openActivity
import skycode.dicoding.jetpack.mvvm.utils.UtilFunctions.loge

@AndroidEntryPoint
class TvShowFragment : Fragment() {
    private lateinit var binding: FragmentTvShowBinding
    private val viewModel by viewModels<TvShowViewModel>()
    private val tvShowAdapter: TvShowAdapter by lazy {
        TvShowAdapter { item -> setDataMovie(item) }
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
        viewModel.getTvShows().observe(viewLifecycleOwner, {
            binding.noDataTV.isVisible(it.results?.size ?: 0 <= 0)
            tvShowAdapter.setTvShows(it.results)
            with(binding.tvListRV) {
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        })

        viewModel.getFailureMessage().observe(viewLifecycleOwner, {
            loge(it.message.toString())
        })

        viewModel.getIsLoadData().observe(viewLifecycleOwner, {
            binding.loadingSKV.isVisible(it)
        })
    }

    private fun setDataMovie(item: ResultTv) {
        context?.openActivity(DetailMovieActivity::class.java) {
            putInt(DetailMovieActivity.EXTRA_ID_MOVIE, item.id ?: 0)
            putString(DetailMovieActivity.EXTRA_TAG, TAG)
        }
    }

    companion object {
        val TAG = TvShowFragment::class.java.simpleName ?: ""
    }
}