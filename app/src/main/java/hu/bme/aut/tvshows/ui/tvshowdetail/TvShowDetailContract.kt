package hu.bme.aut.tvshows.ui.tvshowdetail

import hu.bme.aut.tvshows.ui.model.ShowDetail


interface TvShowDetailContract {

    interface View {
        fun onResultsReady(showDetail: ShowDetail)
        fun onShowAddedToFavourites()
    }

    interface Presenter {
        fun saveShow(show: ShowDetail)
        fun getDetails(id: Long)
        fun cleanup()
    }
}