package hu.bme.aut.tvshows.ui.searchtvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.tvshows.databinding.FrameTvshowBinding


class TvShowListAdapter(seed: Int) : RecyclerView.Adapter<TvShowListAdapter.TvShowViewHolder>() {
    private var number: Int = 0

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
        holder.getView().text =  number.toString()
        number++
    }

    override fun getItemCount(): Int {
        return 100;
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