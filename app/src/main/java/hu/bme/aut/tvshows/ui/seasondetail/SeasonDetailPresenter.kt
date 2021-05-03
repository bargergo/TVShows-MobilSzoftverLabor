package hu.bme.aut.tvshows.ui.seasondetail

import android.util.Log
import hu.bme.aut.tvshows.interactor.DbInteractor
import hu.bme.aut.tvshows.interactor.NetworkInteractor
import hu.bme.aut.tvshows.ui.model.toUIModel
import kotlinx.coroutines.*
import javax.inject.Inject

class SeasonDetailPresenter @Inject constructor(
        private val view: SeasonDetailContract.View,
        private val networkInteractor: NetworkInteractor,
        private val dbInteractor: DbInteractor
) : SeasonDetailContract.Presenter, CoroutineScope by MainScope() {

    override fun getEpisodes(seasonId: Long) {
        launch(Dispatchers.IO) {
            val results = networkInteractor.getEpisodes(seasonId)
            withContext(Dispatchers.Main) {
                view.onEpisodesResult(results.map { it.toUIModel() })
            }
        }
    }

    override fun getEpisodesFromDb(seasonId: Long) {
        launch(Dispatchers.IO) {
            val results = dbInteractor.getEpisodesForSeason(seasonId)
            withContext(Dispatchers.Main) {
                view.onEpisodesResult(results.map { it.toUIModel() })
            }
        }
    }

    override fun cleanup() {
        cancel()
    }
}