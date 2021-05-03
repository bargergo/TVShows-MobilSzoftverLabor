package hu.bme.aut.tvshows.ui.episodedetail

import android.util.Log
import hu.bme.aut.tvshows.dispatchers.DispatcherProvider
import hu.bme.aut.tvshows.interactor.DbInteractor
import hu.bme.aut.tvshows.interactor.NetworkInteractor
import hu.bme.aut.tvshows.ui.model.toUIModel
import kotlinx.coroutines.*
import javax.inject.Inject

class EpisodeDetailPresenter @Inject constructor(
        val view: EpisodeDetailContract.View,
        val networkInteractor: NetworkInteractor,
        val dbInteractor: DbInteractor,
        private val dispatcherProvider: DispatcherProvider
) : EpisodeDetailContract.Presenter, CoroutineScope by MainScope() {
    override fun fetchEpisodeDetails(episodeId: Long) {
        launch(dispatcherProvider.io()) {
            val result = networkInteractor.getEpisode(episodeId)
            withContext(dispatcherProvider.main()) {
                view.onResults(result.toUIModel())
            }
        }
    }

    override fun fetchEpisodeDetailsFromDb(episodeId: Long) {
        launch(dispatcherProvider.io()) {
            val result = dbInteractor.getEpisode(episodeId)
            withContext(dispatcherProvider.main()) {
                view.onResults(result.toUIModel())
            }
        }
    }

    override fun cleanup() {
        cancel()
    }
}