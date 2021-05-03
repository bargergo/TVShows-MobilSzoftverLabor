package hu.bme.aut.tvshows.ui.favouritetvshows

import hu.bme.aut.tvshows.ui.model.Show


interface FavouriteTvShowsContract {

    interface View {
        fun showMessage(message: String)
        fun updateView(shows: List<Show> )
        fun itemRemoved()
    }

    interface Presenter {
        fun getFavouriteTvShows()
        fun removeShow(show: Show)
        fun cleanup()
    }
}