package hu.bme.aut.tvshows.ui.episodedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.databinding.FragmentEpisodedetailBinding
import hu.bme.aut.tvshows.model.Episode
import hu.bme.aut.tvshows.util.stripHtml
import javax.inject.Inject

@AndroidEntryPoint
class EpisodeDetailFragment: Fragment(), EpisodeDetailContract.View {

    @Inject
    lateinit var presenter: EpisodeDetailContract.Presenter

    private var _binding: FragmentEpisodedetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodedetailBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.textView3.text = "This is episode detail fragment"
        val episodeId = arguments?.getInt("episodeId")
        episodeId?.let {
            binding.textView3.text = "Got argument: $it"
            presenter.fetchEpisodeDetails(it)
        }
        return view
    }

    override fun onResults(result: Episode) {
        binding.textView3.text = result.summary?.stripHtml() ?: "No summary added yet"
    }
}