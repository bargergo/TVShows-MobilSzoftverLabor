package hu.bme.aut.tvshows.ui.seasondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.databinding.FragmentSeasondetailBinding
import hu.bme.aut.tvshows.model.Cast
import hu.bme.aut.tvshows.model.Episode
import hu.bme.aut.tvshows.ui.tvshowdetail.CastListAdapter
import javax.inject.Inject

@AndroidEntryPoint
class SeasonDetailFragment : Fragment(), SeasonDetailContract.View {

    @Inject
    lateinit var presenter: SeasonDetailContract.Presenter

    lateinit var episodeListAdapter: EpisodeListAdapter
    var episodeResults: MutableList<Episode> = mutableListOf()

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

        val rvEpisodes = binding.rvEpisodes
        rvEpisodes.setHasFixedSize(true)
        rvEpisodes.setLayoutManager(LinearLayoutManager(view.getContext()))
        rvEpisodes.isNestedScrollingEnabled = false
        episodeListAdapter = EpisodeListAdapter(this, episodeResults)
        rvEpisodes.adapter = episodeListAdapter

        binding.tvTitle.text = "This is Season Detail fragment"
        val seasonId = arguments?.getLong("seasonId")
        val seasonNumber = arguments?.getInt("seasonNumber")
        seasonId?.let {
            presenter.getEpisodes(it)
        }
        seasonNumber?.let {
            binding.tvTitle.text = "Season $it"
        }

        return view
    }

    override fun onDestroy() {
        presenter.cleanup()
        super.onDestroy()
    }

    override fun onEpisodesResult(episodes: List<Episode>) {
        if (episodeResults.isEmpty()) {
            episodeResults.addAll(episodes)
            episodeListAdapter.notifyDataSetChanged()
        }
    }
}