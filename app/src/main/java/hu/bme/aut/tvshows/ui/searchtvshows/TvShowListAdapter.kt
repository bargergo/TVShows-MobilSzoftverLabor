package hu.bme.aut.tvshows.ui.searchtvshows

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import hu.bme.aut.tvshows.R
import hu.bme.aut.tvshows.databinding.ListelementTvshowBinding
import hu.bme.aut.tvshows.model.ShowSearchResult
import hu.bme.aut.tvshows.model.ShowSummary
import hu.bme.aut.tvshows.ui.model.Show
import hu.bme.aut.tvshows.util.hideKeyboard
import hu.bme.aut.tvshows.util.stripHtml


class TvShowListAdapter(val fragment: SearchTvShowsFragment, var tvShows: List<Show>) : RecyclerView.Adapter<TvShowListAdapter.TvShowViewHolder>() {

    val context: Context = fragment.requireContext()

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
        val year = tvShow.premier?.year ?: "N/A"
        holder.tvTitle.text =  "${tvShow.name} ($year)"
        holder.tvGenres.text = tvShow.genres ?: "N/A"
        holder.tvSummary.text = tvShow.summary?.stripHtml() ?: "N/A"

        val image = tvShow.imageUrl
        val options: RequestOptions = RequestOptions()
            .error(R.drawable.ic_broken_image)
            .placeholder(R.drawable.loading_animation)
        Glide.with(context).load(image).apply(options).into(holder.imageView)

        holder.itemView.setOnClickListener {
            fragment.activity?.hideKeyboard()
            val bundle = bundleOf("tvShowId" to tvShow.id, "useDbOnly" to false)
            fragment.findNavController().navigate(R.id.action_nav_searchtvshows_to_nav_tvshowdetail, bundle)
            //Toast.makeText(context, "Clicked on ${tvShow.show.name}", Toast.LENGTH_SHORT).show()
        }

        if (tvShow.isFavourite) {
            holder.binding.ibStar.visibility = View.GONE
            holder.binding.ibUnstar.visibility = View.VISIBLE
        } else {
            holder.binding.ibStar.visibility = View.VISIBLE
            holder.binding.ibUnstar.visibility = View.GONE
        }

        holder.binding.ibStar.setOnClickListener {
            fragment.saveShow(tvShow)
            tvShow.isFavourite = true
            notifyItemChanged(position)
        }

        holder.binding.ibUnstar.setOnClickListener {
            fragment.removeShow(tvShow)
            tvShow.isFavourite = false
            notifyItemChanged(position)
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