package hu.bme.aut.tvshows.ui.searchtvshows

import hu.bme.aut.tvshows.model.ShowSearchResult
import hu.bme.aut.tvshows.model.ShowSummary

interface SearchTvShowsContract {

    interface View {
        fun showMessage(message: String)
        fun onSearchResults(results: List<ShowSearchResult>)
    }

    interface Presenter {
        fun saveShow(show: ShowSummary)
        fun search(keywords: String)
        fun cleanup()
    }
}