package hu.bme.aut.tvshows.ui.seasondetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.tvshows.databinding.ListelementEpisodeBinding
import hu.bme.aut.tvshows.model.Episode

class EpisodeListAdapter(val fragment: Fragment, val episodes: List<Episode>) : RecyclerView.Adapter<EpisodeListAdapter.EpisodeViewHolder>() {

    val context: Context = fragment.requireContext()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
                ListelementEpisodeBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = episodes[position]

        holder.binding.tvNumber.text = episode.number.toString()
        holder.binding.tvTitle.text = episode.name

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "Episode ${episode.number} clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    inner class EpisodeViewHolder(val binding: ListelementEpisodeBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}