package hu.bme.aut.tvshows.ui.favouritetvshows

interface FavouriteTvShowsContract {

    interface View {
        fun showMessage(message: String)
        fun updateView(message: String)
    }

    interface Presenter {
        fun getFavouriteTvShows()
        fun cleanup()
    }
}