package hu.bme.aut.tvshows.ui.favouritetvshows

import hu.bme.aut.tvshows.interactor.DbInteractor
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FavouriteTvShowsPresenter @Inject constructor(
    private val view: FavouriteTvShowsContract.View,
    private val dbInteractor: DbInteractor
) : FavouriteTvShowsContract.Presenter, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override fun getFavouriteTvShows() {
        launch {
            try {
                val favouriteShows = dbInteractor.getFavouriteTvShows()
                withContext(Dispatchers.Main) {
                    view.updateView(favouriteShows.toString())
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    view.showMessage("Something went wrong")
                }
            }

        }
    }

    override fun cleanup() {
        // By default, every coroutine initiated in this context
        // will use the job and dispatcher specified by the
        // coroutineContext.
        // The coroutines are scoped to their execution environment.
        job.cancel()
    }
}