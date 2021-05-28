package hu.bme.aut.tvshows.ui.seasondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.databinding.FragmentSeasondetailBinding
import hu.bme.aut.tvshows.ui.model.Episode
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

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeasondetailBinding.inflate(inflater, container, false)
        val view = binding.root

        val seasonId = arguments?.getLong("seasonId")
        val seasonNumber = arguments?.getInt("seasonNumber")
        val useDbOnly = arguments?.getBoolean("useDbOnly", false) ?: false

        val rvEpisodes = binding.rvEpisodes
        rvEpisodes.setHasFixedSize(true)
        rvEpisodes.setLayoutManager(LinearLayoutManager(view.getContext()))
        rvEpisodes.isNestedScrollingEnabled = false
        episodeListAdapter = EpisodeListAdapter(this, episodeResults, useDbOnly)
        rvEpisodes.adapter = episodeListAdapter

        binding.tvTitle.text = "This is Season Detail fragment"


        seasonId?.let {
            if (useDbOnly) {
                presenter.getEpisodesFromDb(it)
            } else {
                presenter.getEpisodes(it)
            }
        }
        seasonNumber?.let {
            binding.tvTitle.text = "Season $it"
        }

        firebaseAnalytics = Firebase.analytics

        firebaseAnalytics.run {
            val bundle = Bundle().apply {
                putString(FirebaseAnalytics.Param.SCREEN_NAME, SeasonDetailFragment::class.java.simpleName)
                putString(FirebaseAnalytics.Param.SCREEN_CLASS, SeasonDetailFragment::class.java.name)
            }
            logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}