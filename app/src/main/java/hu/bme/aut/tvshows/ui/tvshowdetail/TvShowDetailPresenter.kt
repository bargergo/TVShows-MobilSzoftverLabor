package hu.bme.aut.tvshows.ui.tvshowdetail

import hu.bme.aut.tvshows.data.Cast
import hu.bme.aut.tvshows.data.Episode
import hu.bme.aut.tvshows.data.Season
import hu.bme.aut.tvshows.interactor.DbInteractor
import hu.bme.aut.tvshows.interactor.NetworkInteractor
import hu.bme.aut.tvshows.ui.model.ShowDetail
import hu.bme.aut.tvshows.ui.model.toDataModel
import hu.bme.aut.tvshows.ui.model.toUIModel
import hu.bme.aut.tvshows.util.stripHtml
import kotlinx.coroutines.*
import javax.inject.Inject

class TvShowDetailPresenter @Inject constructor(
    private val view: TvShowDetailContract.View,
    private val networkInteractor: NetworkInteractor,
    private val dbInteractor: DbInteractor
) : TvShowDetailContract.Presenter, CoroutineScope by MainScope() {
    override fun getDetails(id: Long) {
        launch(Dispatchers.IO) {
            val show = networkInteractor.getShow(id)
            val cast = networkInteractor.getCast(id)
            val seasons = networkInteractor.getSeasons(id)
            val favouriteShowIds = dbInteractor.getFavouriteTvShowIds()
            withContext(Dispatchers.Main) {
                val uiModels = show.toUIModel(favouriteShowIds, cast, seasons)
                view.onResultsReady(uiModels)
            }
        }
    }

    override fun getDetailsFromDb(id: Long) {
        launch(Dispatchers.IO) {
            val show = dbInteractor.getShow(id)
            withContext(Dispatchers.Main) {
                val uiModels = show!!.toUIModel()
                view.onResultsReady(uiModels)
            }
        }
    }

    override fun saveShow(show: ShowDetail) {
        launch(Dispatchers.IO) {
            val episodes = mutableListOf<Episode>()
            for (season in show.seasons) {
                val seasonId = season.id!!
                episodes.addAll(networkInteractor
                    .getEpisodes(seasonId)
                    .map { it.toDataModel(seasonId) }
                )
            }
            dbInteractor.insertTvShow(
                show.toDataModel(),
                show.seasons.map { it.toDataModel() },
                episodes,
                show.cast.map { it.toDataModel(show.id) }
            )
            withContext(Dispatchers.Main) {
                view.onShowAddedToFavourites()
            }
        }
    }

    override fun saveShowIfNotSavedYet(show: ShowDetail) {
        launch(Dispatchers.IO) {
            if (dbInteractor.getShow(show.id) == null) {
                val episodes = mutableListOf<Episode>()
                for (season in show.seasons) {
                    val seasonId = season.id!!
                    episodes.addAll(networkInteractor
                        .getEpisodes(seasonId)
                        .map { it.toDataModel(seasonId) }
                    )
                }
                dbInteractor.insertTvShow(
                    show.toDataModel(),
                    show.seasons.map { it.toDataModel() },
                    episodes,
                    show.cast.map { it.toDataModel(show.id) }
                )
            }
            withContext(Dispatchers.Main) {
                view.onShowReadyForEdit()
            }
        }
    }

    override fun removeShow(show: ShowDetail) {
        launch(Dispatchers.IO) {
            dbInteractor.removeTvShow(show.toDataModel())
            withContext(Dispatchers.Main) {
                view.onShowRemovedFromFavourites()
            }
        }
    }

    override fun deleteShow(show: ShowDetail) {
        launch(Dispatchers.IO) {
            networkInteractor.deleteShow(show.id)
            dbInteractor.removeTvShow(show.toDataModel())
            withContext(Dispatchers.Main) {
                view.onShowDeleted()
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