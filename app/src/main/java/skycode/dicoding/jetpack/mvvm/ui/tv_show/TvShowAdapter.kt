package skycode.dicoding.jetpack.mvvm.ui.tv_show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import skycode.dicoding.jetpack.mvvm.R
import skycode.dicoding.jetpack.mvvm.data.model.api.tv_show.ResultTv
import skycode.dicoding.jetpack.mvvm.databinding.RowItemMovieBinding
import skycode.dicoding.jetpack.mvvm.utils.UtilConst.BASE_IMAGE_URL

/**
 * Created by wahyu on 28/05/21
 * Find me on my Github -> https://github.com/wahyuristanto
 **/

class TvShowAdapter(private val listener: (ResultTv) -> Unit) : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private var listCourses = ArrayList<ResultTv>()

    fun setTvShows(tvShows: List<ResultTv>?) {
        if (tvShows == null) return
        this.listCourses.clear()
        this.listCourses.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val rowItemMovieBinding = RowItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(rowItemMovieBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val movie = listCourses[position]
        holder.bindItem(movie, listener)
    }

    override fun getItemCount(): Int = listCourses.size

    class TvShowViewHolder(private val binding: RowItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: ResultTv, listener: (ResultTv) -> Unit) {
            with(binding) {
                val urlImage = BASE_IMAGE_URL+item.backdropPath
                movieTitleTV.text = item.name
                movieDateTV.text = item.firstAirDate
                movieRateTV.text = item.voteAverage.toString()

                val picasso = Picasso.get()
                picasso.setIndicatorsEnabled(true)
                picasso.load(urlImage)
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                    .into(movieImageIV)
                root.setOnClickListener { listener(item) }
            }
        }
    }
}