package hu.bme.aut.tvshows.ui.tvshowdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import hu.bme.aut.tvshows.databinding.FragmentTvshowdetailBinding

class TvShowDetailFragment: Fragment() {

    private var _binding: FragmentTvshowdetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvshowdetailBinding.inflate(inflater, container, false)
        val view = binding.root
        val tvShowId = arguments?.getInt("tvShowId")
        binding.textView2.text = "Got TV Show Id: $tvShowId"
        return view
    }
}