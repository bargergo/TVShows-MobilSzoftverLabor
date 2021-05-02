package hu.bme.aut.tvshows.ui.seasondetail

import hu.bme.aut.tvshows.interactor.NetworkInteractor
import kotlinx.coroutines.*
import javax.inject.Inject

class SeasonDetailPresenter @Inject constructor(
        private val view: SeasonDetailContract.View,
        private val networkInteractor: NetworkInteractor
) : SeasonDetailContract.Presenter, CoroutineScope by MainScope() {

    override fun getEpisodes(seasonId: Long) {
        launch {
            val results = networkInteractor.getEpisodes(seasonId)
            withContext(Dispatchers.Main) {
                view.onEpisodesResult(results)
            }
        }
    }

    override fun cleanup() {
        cancel()
    }
}