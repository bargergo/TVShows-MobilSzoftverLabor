package hu.bme.aut.tvshows.ui.episodedetail

import hu.bme.aut.tvshows.interactor.NetworkInteractor
import kotlinx.coroutines.*
import javax.inject.Inject

class EpisodeDetailPresenter @Inject constructor(
        val view: EpisodeDetailContract.View,
        val networkInteractor: NetworkInteractor
) : EpisodeDetailContract.Presenter, CoroutineScope by MainScope() {
    override fun fetchEpisodeDetails(episodeId: Int) {
        launch {
            val result = networkInteractor.getEpisode(episodeId)
            withContext(Dispatchers.Main) {
                view.onResults(result)
            }
        }
    }

    override fun cleanup() {
        cancel()
    }
}