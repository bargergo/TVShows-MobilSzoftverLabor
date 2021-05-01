package hu.bme.aut.tvshows.ui.tvshowdetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.tvshows.databinding.ListelementSeasonBinding
import hu.bme.aut.tvshows.model.Season

class SeasonListAdapter(val context: Context, val seasons: List<Season>) : RecyclerView.Adapter<SeasonListAdapter.SeasonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        return SeasonViewHolder(
                ListelementSeasonBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        val season = seasons[position]
        holder.binding.tvSeasonNumber.text = "Season ${season.number}"
        holder.binding.tvEpisodes.text = "${season.episodeOrder} episodes"
    }

    override fun getItemCount(): Int {
        return seasons.size
    }

    inner class SeasonViewHolder(val binding: ListelementSeasonBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}