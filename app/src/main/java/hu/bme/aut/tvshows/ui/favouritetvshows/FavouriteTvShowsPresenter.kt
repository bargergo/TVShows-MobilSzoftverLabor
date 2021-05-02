package hu.bme.aut.tvshows.ui.favouritetvshows

import android.util.Log
import hu.bme.aut.tvshows.interactor.DbInteractor
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FavouriteTvShowsPresenter @Inject constructor(
    private val view: FavouriteTvShowsContract.View,
    private val dbInteractor: DbInteractor
) : FavouriteTvShowsContract.Presenter, CoroutineScope by MainScope() {

    override fun getFavouriteTvShows() {
        launch {
            try {
                val favouriteShows = dbInteractor.getFavouriteTvShows()
                withContext(Dispatchers.Main) {
                    view.updateView(favouriteShows)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.d("Favourite", "Exception", e)
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
        cancel()
    }
}