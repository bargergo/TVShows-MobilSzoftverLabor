package hu.bme.aut.tvshows.ui.favouritetvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.databinding.FragmentFavouritetvshowsBinding
import hu.bme.aut.tvshows.model.*
import hu.bme.aut.tvshows.ui.searchtvshows.TvShowListAdapter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
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
    var showSearchResults: MutableList<ShowSummary> = mutableListOf(ShowSummary(
            1,
            "https://www.tvmaze.com/shows/1/under-the-dome",
            "Under the Dome",
            "Scripted",
            "English",
            listOf("Drama",
                    "Science-Fiction",
                    "Thriller"),
            "Ended",
            60,
            LocalDate.parse("2013-06-24", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            "http://www.cbs.com/shows/under-the-dome/",
            Schedule(
                    "22:00",
                    listOf("Thursday")
            ),
            Rating(6.6f),
            96,
            Network(
                    2,
                    "CBS",
                    Country(
                            "United States",
                            "US",
                            "America/New_York"
                    )
            ),
            null,
            null,
            Externals(25988, 264492, "tt1553656"),
            Image(
                    "https://static.tvmaze.com/uploads/images/medium_portrait/81/202627.jpg",
                    "https://static.tvmaze.com/uploads/images/original_untouched/81/202627.jpg"
            ),
            "<p><b>Under the Dome</b> is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.</p>",
            1617697381,
            Links(
                    LinksSelf(
                            "https://api.tvmaze.com/shows/1"
                    ),
                    LinksSelf(
                            "https://api.tvmaze.com/episodes/185054"
                    )
            )
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritetvshowsBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.textFavouritetvshows.text = "This is favourite TV Show Fragment"
        val recyclerView = binding.recyclerview
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.getContext())
        adapter = TvShowListAdapter(this, showSearchResults)
        recyclerView.adapter = adapter
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