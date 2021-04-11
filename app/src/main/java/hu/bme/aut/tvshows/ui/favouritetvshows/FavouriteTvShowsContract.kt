package hu.bme.aut.tvshows.ui.favouritetvshows

interface FavouriteTvShowsContract {

    interface View {
        fun updateView(message: String)
    }

    interface Presenter {
        fun getFavouriteTvShows()
    }
}