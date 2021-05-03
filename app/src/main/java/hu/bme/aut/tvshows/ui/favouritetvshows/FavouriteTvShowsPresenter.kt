package hu.bme.aut.tvshows.ui.favouritetvshows

import android.util.Log
import hu.bme.aut.tvshows.dispatchers.DispatcherProvider
import hu.bme.aut.tvshows.interactor.DbInteractor
import hu.bme.aut.tvshows.ui.model.Show
import hu.bme.aut.tvshows.ui.model.toDataModel
import hu.bme.aut.tvshows.ui.model.toUIModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FavouriteTvShowsPresenter @Inject constructor(
    private val view: FavouriteTvShowsContract.View,
    private val dbInteractor: DbInteractor,
    private val dispatcherProvider: DispatcherProvider
) : FavouriteTvShowsContract.Presenter, CoroutineScope by MainScope() {

    override fun getFavouriteTvShows() {
        launch(dispatcherProvider.io()) {
            try {
                val favouriteShows = dbInteractor
                    .getFavouriteTvShows()
                    .map { it.toUIModel() }
                withContext(dispatcherProvider.main()) {
                    view.updateView(favouriteShows)
                }
            } catch (e: Exception) {
                withContext(dispatcherProvider.main()) {
                    Log.d("Favourite", "Exception", e)
                    view.showMessage("Something went wrong")
                }
            }

        }
    }

    override fun removeShow(show: Show) {
        launch(dispatcherProvider.io()) {
            dbInteractor.removeTvShow(show.toDataModel())
            withContext(dispatcherProvider.main()) {
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