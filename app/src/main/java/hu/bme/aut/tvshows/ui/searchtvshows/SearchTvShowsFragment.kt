package hu.bme.aut.tvshows.ui.searchtvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import hu.bme.aut.tvshows.R

class SearchTvShowsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_searchtvshows, container, false)
        val textView: TextView = root.findViewById(R.id.text_searchtvshows)
        textView.text = "This is search TV Shows Fragment"
        return root
    }
}