package hu.bme.aut.tvshows.ui.searchtvshows

interface SearchTvShowsContract {

    interface View {
        fun onSearchResults(results: String)
    }

    interface Presenter {
        fun search(keywords: String)
    }
}