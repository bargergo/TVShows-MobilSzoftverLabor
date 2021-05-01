package hu.bme.aut.tvshows.ui.tvshowdetail

import hu.bme.aut.tvshows.interactor.NetworkInteractor
import kotlinx.coroutines.*
import javax.inject.Inject

class TvShowDetailPresenter @Inject constructor(
    private val view: TvShowDetailContract.View,
    private val networkInteractor: NetworkInteractor
) : TvShowDetailContract.Presenter, CoroutineScope by MainScope() {
    override fun getDetails(id: Int) {
        launch {
            val show = networkInteractor.getShow(id)
            val cast = networkInteractor.getCast(id)
            val seasons = networkInteractor.getSeasons(id)
            withContext(Dispatchers.Main) {
                view.onResultsReady(show, cast, seasons)
            }
        }
    }

    override fun cleanup() {
        // By default, every coroutine initiated in this context
        // will use the job and dispatcher specified by the
        // coroutineContext.
        // The coroutines are scoped to their execution environment.
        cancel()
    }
}