package hu.bme.aut.tvshows.ui.seasondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.bme.aut.tvshows.databinding.FragmentSeasondetailBinding

class SeasonDetailFragment : Fragment() {

    private var _binding: FragmentSeasondetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeasondetailBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.textView2.text = "This is Season Detail fragment"
        val seasonId = arguments?.getInt("seasonId")
        seasonId?.let {
            binding.textView2.text = "Got argument: $it"
        }

        return view
    }
}