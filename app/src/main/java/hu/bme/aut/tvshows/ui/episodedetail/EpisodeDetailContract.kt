package hu.bme.aut.tvshows.ui.episodedetail

import hu.bme.aut.tvshows.ui.model.Episode

interface EpisodeDetailContract {

    interface View {
        fun onResults(result: Episode)
    }

    interface Presenter {
        fun fetchEpisodeDetails(episodeId: Long)
        fun fetchEpisodeDetailsFromDb(episodeId: Long)
        fun cleanup()
    }
}