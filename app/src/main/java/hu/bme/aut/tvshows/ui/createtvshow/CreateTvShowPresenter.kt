package hu.bme.aut.tvshows.ui.createtvshow

import hu.bme.aut.tvshows.interactor.DbInteractor
import hu.bme.aut.tvshows.interactor.NetworkInteractor
import hu.bme.aut.tvshows.model.*
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CreateTvShowPresenter @Inject constructor(
    private val view: CreateTvShowContract.View,
    private val dbInteractor: DbInteractor,
    private val networkInteractor: NetworkInteractor
) : CreateTvShowContract.Presenter, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override fun onCreateTvShow(data: ShowData) {
        launch {

            try {
                dbInteractor.insertTvShow(data.toString())
                networkInteractor.createShow(data)
                withContext(Dispatchers.Main) {
                    view.showMessage("Successfully created TV Show")
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