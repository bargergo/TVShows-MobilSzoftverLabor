package hu.bme.aut.tvshows.ui.episodedetail

import hu.bme.aut.tvshows.model.Episode

interface EpisodeDetailContract {

    interface View {
        fun onResults(result: Episode)
    }

    interface Presenter {
        fun fetchEpisodeDetails(episodeId: Int)
        fun cleanup()
    }
}