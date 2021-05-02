package hu.bme.aut.tvshows.ui.edittvshow

import hu.bme.aut.tvshows.interactor.DbInteractor
import hu.bme.aut.tvshows.interactor.NetworkInteractor
import kotlinx.coroutines.*
import javax.inject.Inject

class EditTvShowPresenter @Inject constructor(
    private val view: EditTvShowContract.View,
    private val networkInteractor: NetworkInteractor,
    private val dbInteractor: DbInteractor
): EditTvShowContract.Presenter, CoroutineScope by MainScope() {

    override fun loadShowData(id: Long) {
        launch(Dispatchers.IO) {
            val show = dbInteractor.getShow(id)!!
            withContext(Dispatchers.Main) {
                view.onShowDataLoaded(show)
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