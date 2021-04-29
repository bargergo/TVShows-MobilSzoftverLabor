package hu.bme.aut.tvshows.ui.tvshowdetail

import hu.bme.aut.tvshows.model.Cast
import hu.bme.aut.tvshows.model.Season
import hu.bme.aut.tvshows.model.ShowDetails

interface TvShowDetailContract {

    interface View {
        fun onResultsReady(showDetail: ShowDetails, cast: List<Cast>, seasons: List<Season>)
    }

    interface Presenter {
        fun getDetails(id: Int)
        fun cleanup()
    }
}