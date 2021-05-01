package hu.bme.aut.tvshows.ui.episodedetail

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

class EpisodeDetailPresenter : EpisodeDetailContract.Presenter, CoroutineScope by MainScope() {
    override fun cleanup() {
        cancel()
    }
}