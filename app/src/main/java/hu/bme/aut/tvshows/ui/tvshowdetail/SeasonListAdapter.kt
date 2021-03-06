package hu.bme.aut.tvshows.ui.tvshowdetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.tvshows.R
import hu.bme.aut.tvshows.databinding.ListelementSeasonBinding
import hu.bme.aut.tvshows.ui.model.Season

class SeasonListAdapter(val fragment: Fragment, val seasons: List<Season>, val useDbOnly: Boolean) : RecyclerView.Adapter<SeasonListAdapter.SeasonViewHolder>() {

    val context: Context = fragment.requireContext()

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
        holder.binding.tvEpisodes.text = "${season.numberOfEpisodes} episodes"

        holder.itemView.setOnClickListener {
            val bundle = bundleOf("seasonId" to season.id, "seasonNumber" to season.number, "useDbOnly" to useDbOnly)
            fragment.findNavController().navigate(R.id.action_nav_tvshowdetail_to_nav_seasondetail, bundle)
        }
    }

    override fun getItemCount(): Int {
        return seasons.size
    }

    inner class SeasonViewHolder(val binding: ListelementSeasonBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}