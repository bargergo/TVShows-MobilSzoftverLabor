package hu.bme.aut.tvshows.ui.createtvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import hu.bme.aut.tvshows.R

class CreateTvShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_createtvshow, container, false)
        val textView: TextView = root.findViewById(R.id.text_createtvshow)
        textView.text = "This is create TV Show Fragment"
        return root
    }
}