package hu.bme.aut.tvshows.ui.episodedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.databinding.FragmentEpisodedetailBinding
import hu.bme.aut.tvshows.ui.edittvshow.EditTvShowFragment
import hu.bme.aut.tvshows.ui.model.Episode
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

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodedetailBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.tvTitle.text = "Title"
        binding.tvSummary.text = "Summary"
        val episodeId = arguments?.getLong("episodeId")
        val useDbOnly = arguments?.getBoolean("useDbOnly", false) ?: false
        episodeId?.let {
            if (useDbOnly) {
                presenter.fetchEpisodeDetailsFromDb(it)
            } else {
                presenter.fetchEpisodeDetails(it)
            }
        }

        firebaseAnalytics = Firebase.analytics

        firebaseAnalytics.run {
            val bundle = Bundle().apply {
                putString(FirebaseAnalytics.Param.SCREEN_NAME, EpisodeDetailFragment::class.java.simpleName)
                putString(FirebaseAnalytics.Param.SCREEN_CLASS, EpisodeDetailFragment::class.java.name)
            }
            logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
        }

        return view
    }

    override fun onResults(result: Episode) {
        binding.tvTitle.text = "S${result.season}E${result.number} ${result.name}"
        binding.tvSummary.text = result.summary?.stripHtml() ?: "No summary added yet"
    }
}