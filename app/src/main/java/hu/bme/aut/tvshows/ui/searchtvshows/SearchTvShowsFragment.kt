package hu.bme.aut.tvshows.ui.searchtvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.bme.aut.tvshows.databinding.FragmentSearchtvshowsBinding

class SearchTvShowsFragment : Fragment() {

    private var _binding: FragmentSearchtvshowsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchtvshowsBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.textSearchtvshows.text = "This is search TV Shows Fragment"
        return view
    }
}