package hu.bme.aut.tvshows.ui.searchtvshows

import hu.bme.aut.tvshows.ui.model.Show

interface SearchTvShowsContract {

    interface View {
        fun showMessage(message: String)
        fun onSearchResults(results: List<Show>)
    }

    interface Presenter {
        fun saveShow(show: Show)
        fun removeShow(show: Show)
        fun search(keywords: String)
        fun cleanup()
    }
}