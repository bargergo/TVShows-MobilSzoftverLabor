package hu.bme.aut.tvshows.ui.favouritetvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import hu.bme.aut.tvshows.R

class FavouriteTvShowsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favouritetvshows, container, false)
        val textView: TextView = root.findViewById(R.id.text_favouritetvshows)
        textView.text = "This is favourite TV Shows Fragment"
        return root
    }
}