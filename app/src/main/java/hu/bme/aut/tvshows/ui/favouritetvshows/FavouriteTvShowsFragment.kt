package hu.bme.aut.tvshows.ui.favouritetvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.bme.aut.tvshows.databinding.FragmentFavouritetvshowsBinding

class FavouriteTvShowsFragment : Fragment() {

    private var _binding: FragmentFavouritetvshowsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritetvshowsBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.textFavouritetvshows.text = "This is favourite TV Show Fragment"
        return view
    }
}