package hu.bme.aut.tvshows.ui.createtvshow

import hu.bme.aut.tvshows.data.Season
import hu.bme.aut.tvshows.model.ShowData

interface CreateTvShowContract {

    interface View {
        fun showMessage(message: String)
    }

    interface Presenter {
        fun createTvShow(data: ShowData)
        fun cleanup()
    }
}