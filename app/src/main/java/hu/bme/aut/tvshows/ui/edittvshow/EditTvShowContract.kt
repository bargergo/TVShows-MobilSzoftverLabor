package hu.bme.aut.tvshows.ui.edittvshow

import hu.bme.aut.tvshows.data.ShowWithSeasonsAndEpisodesAndCast

interface EditTvShowContract {

    interface View {
        fun onShowDataLoaded(data: ShowWithSeasonsAndEpisodesAndCast)
    }

    interface Presenter {
        fun loadShowData(id: Long)
        fun cleanup()
    }
}