package hu.bme.aut.tvshows.ui.createtvshow

import android.util.Log
import hu.bme.aut.tvshows.data.Season
import hu.bme.aut.tvshows.data.Show
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
    private val networkInteractor: NetworkInteractor
) : CreateTvShowContract.Presenter, CoroutineScope by MainScope() {

    override fun onCreateTvShow(data: ShowData, seasons: List<Season>) {
        launch {

            try {
                //networkInteractor.createShow(data)
                withContext(Dispatchers.Main) {
                    view.showMessage("Successfully created TV Show")
                }
                dbInteractor.insertTvShow(Show(
                    null,
                    data.name,
                    LocalDate.now(),
                    "comedy, drama",
                    data.summary,
                    data.image?.medium ?: ""
                ), seasons)
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
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