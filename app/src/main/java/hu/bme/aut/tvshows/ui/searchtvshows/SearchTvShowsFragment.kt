package hu.bme.aut.tvshows.ui.searchtvshows

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.tvshows.R
import hu.bme.aut.tvshows.databinding.FragmentSearchtvshowsBinding
import hu.bme.aut.tvshows.model.ShowSearchResult
import hu.bme.aut.tvshows.util.DebouncingQueryTextListener
import javax.inject.Inject

@AndroidEntryPoint
class SearchTvShowsFragment : Fragment(), SearchTvShowsContract.View {

    @Inject
    lateinit var presenter: SearchTvShowsContract.Presenter

    private var _binding: FragmentSearchtvshowsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var adapter: TvShowListAdapter
    var showSearchResults: MutableList<ShowSearchResult> = mutableListOf()

    init {
        setHasOptionsMenu(true);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchtvshowsBinding.inflate(inflater, container, false)
        val view = binding.root
        val recyclerView = binding.recyclerview
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(view.getContext()))
        adapter = TvShowListAdapter(showSearchResults)
        recyclerView.setAdapter(adapter)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu);

        val search = menu.findItem(R.id.nav_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint= "Search TV Shows"

        searchView.setOnQueryTextListener(DebouncingQueryTextListener(
                this@SearchTvShowsFragment.lifecycle
        ) { searchText ->
                searchText?.let {
                    if (it.isEmpty()) {
                        Toast.makeText(activity, "Reset search", Toast.LENGTH_SHORT).show()
                    } else {
                        presenter.search(it)
                    }

                }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onSearchResults(results: List<ShowSearchResult>) {
        showSearchResults.clear()
        showSearchResults.addAll(results)
        adapter.notifyDataSetChanged()
        val resultText = results.map { it.show.name }.joinToString(",\n")
        Toast.makeText(activity, resultText, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        presenter.cleanup()
        super.onDestroy()
    }
}