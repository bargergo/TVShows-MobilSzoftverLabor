package hu.bme.aut.tvshows.ui.favouritetvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.databinding.FragmentFavouritetvshowsBinding
import hu.bme.aut.tvshows.ui.model.Show
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
    var showList: MutableList<Show> = mutableListOf()

    private lateinit var firebaseAnalytics: FirebaseAnalytics

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
        adapter = TvShowListAdapter(this, showList)
        recyclerView.adapter = adapter
        presenter.getFavouriteTvShows()

        firebaseAnalytics = Firebase.analytics

        firebaseAnalytics.run {
            val bundle = Bundle().apply {
                putString(FirebaseAnalytics.Param.SCREEN_NAME, FavouriteTvShowsFragment::class.java.simpleName)
                putString(FirebaseAnalytics.Param.SCREEN_CLASS, FavouriteTvShowsFragment::class.java.name)
            }
            logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
        }

        return view
    }

    override fun updateView(shows: List<Show>) {
        showList.clear()
        showList.addAll(shows)
        adapter.notifyDataSetChanged()
    }

    override fun itemRemoved() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        presenter.cleanup()
        super.onDestroy()
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    fun removeShow(tvShow: Show) {
        presenter.removeShow(tvShow)
        showList.remove(tvShow)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}