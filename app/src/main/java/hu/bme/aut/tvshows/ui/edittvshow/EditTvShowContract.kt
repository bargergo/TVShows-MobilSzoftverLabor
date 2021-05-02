package hu.bme.aut.tvshows.ui.edittvshow

import hu.bme.aut.tvshows.data.ShowWithSeasonsAndEpisodesAndCast
import hu.bme.aut.tvshows.model.ShowData

interface EditTvShowContract {

    interface View {
        fun onShowDataLoaded(data: ShowWithSeasonsAndEpisodesAndCast)
        fun showMessage(message: String)
    }

    interface Presenter {
        fun loadShowData(id: Long)
        fun updateShow(id: Long, data: ShowData)
        fun cleanup()
    }
}