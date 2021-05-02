package hu.bme.aut.tvshows.ui.tvshowdetail

import hu.bme.aut.tvshows.ui.model.ShowDetail


interface TvShowDetailContract {

    interface View {
        fun onResultsReady(showDetail: ShowDetail)
        fun onShowAddedToFavourites()
        fun onShowRemovedFromFavourites()
        fun onShowDeleted()
        fun onShowReadyForEdit()
    }

    interface Presenter {
        fun saveShow(show: ShowDetail)
        fun saveShowIfNotSavedYet(show: ShowDetail)
        fun removeShow(show: ShowDetail)
        fun deleteShow(show: ShowDetail)
        fun getDetails(id: Long)
        fun getDetailsFromDb(id: Long)
        fun cleanup()
    }
}