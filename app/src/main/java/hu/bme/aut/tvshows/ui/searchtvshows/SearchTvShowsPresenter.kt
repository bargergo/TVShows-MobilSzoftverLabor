package hu.bme.aut.tvshows.ui.searchtvshows

import hu.bme.aut.tvshows.interactor.NetworkInteractor
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SearchTvShowsPresenter @Inject constructor(
    private val view: SearchTvShowsContract.View,
    private val networkInteractor: NetworkInteractor
) : SearchTvShowsContract.Presenter, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override fun search(keywords: String) {
        launch {
            val result = networkInteractor.searchShows(keywords)
            withContext(Dispatchers.Main) {
                view.onSearchResults(result.toString())
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