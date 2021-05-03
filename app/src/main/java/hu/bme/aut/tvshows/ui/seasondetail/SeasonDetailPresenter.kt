package hu.bme.aut.tvshows.ui.seasondetail

import android.util.Log
import hu.bme.aut.tvshows.dispatchers.DispatcherProvider
import hu.bme.aut.tvshows.interactor.DbInteractor
import hu.bme.aut.tvshows.interactor.NetworkInteractor
import hu.bme.aut.tvshows.ui.model.toUIModel
import kotlinx.coroutines.*
import javax.inject.Inject

class SeasonDetailPresenter @Inject constructor(
        private val view: SeasonDetailContract.View,
        private val networkInteractor: NetworkInteractor,
        private val dbInteractor: DbInteractor,
        private val dispatcherProvider: DispatcherProvider
) : SeasonDetailContract.Presenter, CoroutineScope by MainScope() {

    override fun getEpisodes(seasonId: Long) {
        launch(dispatcherProvider.io()) {
            val results = networkInteractor.getEpisodes(seasonId)
            withContext(dispatcherProvider.main()) {
                view.onEpisodesResult(results.map { it.toUIModel() })
            }
        }
    }

    override fun getEpisodesFromDb(seasonId: Long) {
        launch(dispatcherProvider.io()) {
            val results = dbInteractor.getEpisodesForSeason(seasonId)
            withContext(dispatcherProvider.main()) {
                view.onEpisodesResult(results.map { it.toUIModel() })
            }
        }
    }

    override fun cleanup() {
        cancel()
    }
}