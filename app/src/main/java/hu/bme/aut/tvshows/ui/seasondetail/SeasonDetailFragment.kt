package hu.bme.aut.tvshows.ui.seasondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.databinding.FragmentSeasondetailBinding
import hu.bme.aut.tvshows.model.Episode
import javax.inject.Inject

@AndroidEntryPoint
class SeasonDetailFragment : Fragment(), SeasonDetailContract.View {

    @Inject
    lateinit var presenter: SeasonDetailContract.Presenter

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
            presenter.getEpisodes(it)
        }

        return view
    }

    override fun onDestroy() {
        presenter.cleanup()
        super.onDestroy()
    }

    override fun onEpisodesResult(episodes: List<Episode>) {
        binding.textView2.text = episodes.map { "Episode ${it.number}: ${it.name}" }.joinToString("\n")
    }
}