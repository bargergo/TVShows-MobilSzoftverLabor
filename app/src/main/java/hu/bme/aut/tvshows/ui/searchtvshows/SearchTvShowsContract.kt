package hu.bme.aut.tvshows.ui.searchtvshows

import hu.bme.aut.tvshows.model.ShowSearchResult

interface SearchTvShowsContract {

    interface View {
        fun onSearchResults(results: List<ShowSearchResult>)
    }

    interface Presenter {
        fun search(keywords: String)
        fun cleanup()
    }
}