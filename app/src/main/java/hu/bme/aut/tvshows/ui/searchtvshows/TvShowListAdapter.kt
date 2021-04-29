package hu.bme.aut.tvshows.ui.searchtvshows

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.tvshows.databinding.FrameTvshowBinding
import hu.bme.aut.tvshows.model.ShowSearchResult


class TvShowListAdapter(var tvShows: List<ShowSearchResult>) : RecyclerView.Adapter<TvShowListAdapter.TvShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {

        return TvShowViewHolder(
            FrameTvshowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.getView().text =  "${tvShows[position].show.name} (${tvShows[position].show.premiered})"
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

    inner class TvShowViewHolder(val binding: FrameTvshowBinding) : RecyclerView.ViewHolder(binding.root) {

        private var view: TextView
        init {
            view = binding.randomText
        }

        fun getView(): TextView {
            return view
        }
    }
}