package hu.bme.aut.tvshows.ui.searchtvshows

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import hu.bme.aut.tvshows.R
import hu.bme.aut.tvshows.databinding.ListelementTvshowBinding
import hu.bme.aut.tvshows.model.ShowSearchResult
import hu.bme.aut.tvshows.util.stripHtml


class TvShowListAdapter(val context: Context, var tvShows: List<ShowSearchResult>) : RecyclerView.Adapter<TvShowListAdapter.TvShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {

        return TvShowViewHolder(
            ListelementTvshowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = tvShows[position]
        val year = tvShow.show.premiered?.year ?: "N/A"
        holder.tvTitle.text =  "${tvShow.show.name} ($year)"
        holder.tvGenres.text = if (tvShow.show.genres.size > 0)
            tvShow.show.genres.joinToString(", ")
        else
            "N/A"
        holder.tvSummary.text = tvShow.show.summary?.stripHtml() ?: "N/A"

        val image = tvShow.show.image
        image?.let {
            val options: RequestOptions = RequestOptions()
                .error(R.drawable.ic_broken_image)
                .placeholder(R.drawable.loading_animation)
            Glide.with(context).load(it.medium).apply(options).into(holder.imageView)
        }
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

    inner class TvShowViewHolder(val binding: ListelementTvshowBinding) : RecyclerView.ViewHolder(binding.root) {

        val tvTitle: TextView = binding.tvTitle
        val tvGenres: TextView = binding.tvGenres
        val tvSummary: TextView = binding.tvSummary
        val imageView: ImageView = binding.ivCover
    }
}