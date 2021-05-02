package hu.bme.aut.tvshows.ui.favouritetvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.data.Show
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
    lateinit var adapter: TvShowListAdapter
    var showSearchResults: MutableList<Show> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritetvshowsBinding.inflate(inflater, container, false)
        val view = binding.root
        val recyclerView = binding.recyclerview
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.getContext())
        adapter = TvShowListAdapter(this, showSearchResults)
        recyclerView.adapter = adapter
        presenter.getFavouriteTvShows()
        return view
    }

    override fun updateView(shows: List<Show>) {
        showSearchResults.clear()
        showSearchResults.addAll(shows)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        presenter.cleanup()
        super.onDestroy()
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}