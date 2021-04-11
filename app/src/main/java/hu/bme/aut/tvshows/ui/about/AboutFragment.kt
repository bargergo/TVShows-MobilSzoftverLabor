package hu.bme.aut.tvshows.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.R
import hu.bme.aut.tvshows.Vehicle
import javax.inject.Inject

@AndroidEntryPoint
class AboutFragment : Fragment() {

    @Inject
    lateinit var vehicle: Vehicle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_about, container, false)
        val textView: TextView = root.findViewById(R.id.text_about)
        textView.text = "This is about Fragment ${vehicle.type}"
        return root
    }
}