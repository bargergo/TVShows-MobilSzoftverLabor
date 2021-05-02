package hu.bme.aut.tvshows.ui.seasondetail

import hu.bme.aut.tvshows.model.Episode

interface SeasonDetailContract {

    interface View {
        fun onEpisodesResult(episodes: List<Episode> )
    }

    interface Presenter {
        fun getEpisodes(seasonId: Long)
        fun cleanup()
    }
}