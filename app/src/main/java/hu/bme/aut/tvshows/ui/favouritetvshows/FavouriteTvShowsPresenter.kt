package hu.bme.aut.tvshows.ui.favouritetvshows

import android.util.Log
import hu.bme.aut.tvshows.interactor.DbInteractor
import hu.bme.aut.tvshows.ui.model.Show
import hu.bme.aut.tvshows.ui.model.toDataModel
import hu.bme.aut.tvshows.ui.model.toUIModel
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
                val favouriteShows = dbInteractor
                    .getFavouriteTvShows()
                    .map { it.toUIModel() }
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

    override fun removeShow(show: Show) {
        launch {
            dbInteractor.removeTvShow(show.toDataModel())
            withContext(Dispatchers.Main) {
                view.itemRemoved()
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