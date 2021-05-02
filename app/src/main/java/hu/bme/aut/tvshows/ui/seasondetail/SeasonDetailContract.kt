package hu.bme.aut.tvshows.ui.seasondetail

import hu.bme.aut.tvshows.ui.model.Episode


interface SeasonDetailContract {

    interface View {
        fun onEpisodesResult(episodes: List<Episode>)
    }

    interface Presenter {
        fun getEpisodes(seasonId: Long)
        fun getEpisodesFromDb(seasonId: Long)
        fun cleanup()
    }
}