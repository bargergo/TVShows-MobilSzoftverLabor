package hu.bme.aut.tvshows.ui.tvshowdetail

import hu.bme.aut.tvshows.ui.model.ShowDetail


interface TvShowDetailContract {

    interface View {
        fun onResultsReady(showDetail: ShowDetail)
        fun onShowAddedToFavourites()
        fun onShowRemovedFromFavourites()
    }

    interface Presenter {
        fun saveShow(show: ShowDetail)
        fun removeShow(show: ShowDetail)
        fun getDetails(id: Long)
        fun cleanup()
    }
}