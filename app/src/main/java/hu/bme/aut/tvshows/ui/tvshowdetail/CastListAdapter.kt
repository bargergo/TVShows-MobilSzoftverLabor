package hu.bme.aut.tvshows.ui.tvshowdetail

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import hu.bme.aut.tvshows.R
import hu.bme.aut.tvshows.databinding.ListelementCastBinding
import hu.bme.aut.tvshows.model.Cast

class CastListAdapter(val context: Context, val cast: List<Cast>) : RecyclerView.Adapter<CastListAdapter.CastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(
                ListelementCastBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val castElement = cast[position]

        holder.tvCharacter.text = castElement.character.name
        holder.tvActor.text = castElement.person.name

        val image = castElement.character.image
        image?.let {
            val options: RequestOptions = RequestOptions()
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.loading_animation)
            Glide.with(context).load(it.medium).apply(options).into(holder.ivActor)
        }

    }

    override fun getItemCount(): Int {
        return cast.size
    }

    inner class CastViewHolder(val binding: ListelementCastBinding) : RecyclerView.ViewHolder(binding.root) {

        val ivActor: ImageView = binding.ivActor
        val tvCharacter: TextView = binding.tvCharacter
        val tvActor: TextView = binding.tvActor
    }
}