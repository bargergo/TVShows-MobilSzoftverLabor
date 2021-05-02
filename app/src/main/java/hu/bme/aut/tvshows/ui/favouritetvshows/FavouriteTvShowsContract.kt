package hu.bme.aut.tvshows.ui.favouritetvshows

import hu.bme.aut.tvshows.data.Show

interface FavouriteTvShowsContract {

    interface View {
        fun showMessage(message: String)
        fun updateView(shows: List<Show> )
    }

    interface Presenter {
        fun getFavouriteTvShows()
        fun cleanup()
    }
}