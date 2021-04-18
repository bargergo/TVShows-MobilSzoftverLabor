package hu.bme.aut.tvshows.ui.favouritetvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.databinding.FragmentFavouritetvshowsBinding
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteTvShowsFragment : Fragment(), FavouriteTvShowsContract.View {

    @Inject
    lateinit var presenter: FavouriteTvShowsContract.Presenter

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
        binding.btnClickMe.setOnClickListener {
            presenter.getFavouriteTvShows()
        }
        return view
    }

    override fun updateView(message: String) {
        binding.textFavouritetvshows.text = message
    }

    override fun onDestroy() {
        presenter.cleanup()
        super.onDestroy()
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}