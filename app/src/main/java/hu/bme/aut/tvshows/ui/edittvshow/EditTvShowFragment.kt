package hu.bme.aut.tvshows.ui.edittvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.data.ShowWithSeasonsAndEpisodesAndCast
import hu.bme.aut.tvshows.databinding.FragmentEdittvshowBinding
import javax.inject.Inject

@AndroidEntryPoint
class EditTvShowFragment: Fragment(), EditTvShowContract.View {

    @Inject
    lateinit var presenter: EditTvShowContract.Presenter

    private var _binding: FragmentEdittvshowBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEdittvshowBinding.inflate(inflater, container, false)
        val view = binding.root

        val showId = arguments?.getLong("showId")
        showId?.let {
            presenter.loadShowData(showId)
        }
        return view
    }

    override fun onShowDataLoaded(data: ShowWithSeasonsAndEpisodesAndCast) {
        binding.etTitle.setText(data.show.name)
        binding.etGenres.setText(data.show.genres)
        binding.etPremiered.setText(data.show.premier.toString())
        binding.etImageUrl.setText(data.show.imageUrl)
        binding.etSummary.setText(data.show.summary)
    }

    override fun onDestroy() {
        presenter.cleanup()
        super.onDestroy()
    }
}