package hu.bme.aut.tvshows.ui.createtvshow

import android.util.Log
import hu.bme.aut.tvshows.data.Season
import hu.bme.aut.tvshows.data.Show
import hu.bme.aut.tvshows.dispatchers.DispatcherProvider
import hu.bme.aut.tvshows.interactor.DbInteractor
import hu.bme.aut.tvshows.interactor.NetworkInteractor
import hu.bme.aut.tvshows.model.*
import kotlinx.coroutines.*
import org.threeten.bp.LocalDate
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CreateTvShowPresenter @Inject constructor(
    private val view: CreateTvShowContract.View,
    private val dbInteractor: DbInteractor,
    private val networkInteractor: NetworkInteractor,
    private val dispatcherProvider: DispatcherProvider
) : CreateTvShowContract.Presenter, CoroutineScope by MainScope() {

    override fun createTvShow(data: ShowData) {
        launch(dispatcherProvider.io()) {

            try {
                networkInteractor.createShow(data)
                dbInteractor.insertTvShow(Show(
                    null,
                    data.name,
                    data.premiered,
                    data.genres.joinToString(", "),
                    data.summary,
                    data.image?.original ?: ""
                ), emptyList(), emptyList(), emptyList())
                withContext(dispatcherProvider.main()) {
                    view.showMessage("Successfully created TV Show")
                }
            } catch (e: Exception) {
                withContext(dispatcherProvider.main()) {
                    Log.d("CreateTVP", "Exception", e)
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