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
import hu.bme.aut.tvshows.dispatchers.DispatcherProvider
import hu.bme.aut.tvshows.ui.model.Show
import hu.bme.aut.tvshows.util.DebouncingQueryTextListener
import javax.inject.Inject

@AndroidEntryPoint
class SearchTvShowsFragment : Fragment(), SearchTvShowsContract.View {

    @Inject
    lateinit var presenter: SearchTvShowsContract.Presenter

    @Inject
    lateinit var dispatcherProvider: DispatcherProvider

    private var _binding: FragmentSearchtvshowsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var adapter: TvShowListAdapter
    var showSearchResults: MutableList<Show> = mutableListOf()

    private var keywords = ""

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
        recyclerView.layoutManager = LinearLayoutManager(view.getContext())
        adapter = TvShowListAdapter(this, showSearchResults)
        recyclerView.adapter = adapter

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu);

        val search = menu.findItem(R.id.nav_search)
        val searchView = search.actionView as SearchView
        searchView.isIconifiedByDefault = false
        searchView.queryHint= "Search TV Shows"

        if (keywords.isNotEmpty()) {
            searchView.setQuery(keywords, false)
            adapter.notifyDataSetChanged()
            changeVisibility(showSearchResults)
        }


        searchView.setOnQueryTextListener(DebouncingQueryTextListener(
                this@SearchTvShowsFragment.lifecycle,
                dispatcherProvider
        ) { searchText ->
                searchText?.let {
                    if (it.isEmpty()) {
                        showSearchResults.clear()
                        adapter.notifyDataSetChanged()
                        binding.emptyView.visibility = View.VISIBLE
                        binding.noDataView.visibility = View.GONE
                        binding.recyclerview.visibility = View.GONE
                    } else {
                        keywords = it
                        presenter.search(it)
                    }

                }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onSearchResults(results: List<Show>) {
        showSearchResults.clear()
        showSearchResults.addAll(results)
        adapter.notifyDataSetChanged()
        changeVisibility(results)
    }

    private fun changeVisibility(results: List<Show>) {
        binding.emptyView.visibility = View.GONE
        if (results.size > 0) {
            binding.noDataView.visibility = View.GONE
            binding.recyclerview.visibility = View.VISIBLE
        } else {
            binding.noDataView.visibility = View.VISIBLE
            binding.recyclerview.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        presenter.cleanup()
        super.onDestroy()
    }

    fun saveShow(show: Show) {
        presenter.saveShow(show)
    }

    fun removeShow(show: Show) {
        presenter.removeShow(show)
    }
}