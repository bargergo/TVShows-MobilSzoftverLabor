package hu.bme.aut.tvshows.ui.searchtvshows

import hu.bme.aut.tvshows.data.Cast
import hu.bme.aut.tvshows.data.Episode
import hu.bme.aut.tvshows.data.Season
import hu.bme.aut.tvshows.data.Show
import hu.bme.aut.tvshows.interactor.DbInteractor
import hu.bme.aut.tvshows.interactor.NetworkInteractor
import hu.bme.aut.tvshows.util.stripHtml
import kotlinx.coroutines.*
import javax.inject.Inject

class SearchTvShowsPresenter @Inject constructor(
    private val view: SearchTvShowsContract.View,
    private val networkInteractor: NetworkInteractor,
    private val dbInteractor: DbInteractor
) : SearchTvShowsContract.Presenter, CoroutineScope by MainScope() {

    override fun saveShow(show: hu.bme.aut.tvshows.ui.model.Show) {
        launch {
            val seasons = networkInteractor.getSeasons(show.id)
            val episodes = mutableListOf<Episode>()
            for (season in seasons) {
                val episodesForSeason = networkInteractor
                    .getEpisodes(season.id)
                    .map {
                        Episode(
                            it.id,
                            season.id,
                            it.number,
                            it.name,
                            it.season,
                            it.summary?.stripHtml()
                        )
                    }
                episodes.addAll(episodesForSeason)
            }
            val cast = networkInteractor.getCast(show.id)
            dbInteractor.insertTvShow(
                Show(
                    id = show.id,
                    name = show.name,
                    premier = show.premier,
                    genres = show.genres,
                    summary = show.summary,
                    imageUrl = show.imageUrl
                ),
                seasons.map {
                            Season(
                                it.id,
                                it.number,
                                show.id
                            )
                },
                episodes,
                cast.map {
                    Cast(
                        it.character.id,
                        show.id,
                        it.character.image?.medium,
                        it.character.name,
                        it.person.name
                    )
                }
            )
            withContext(Dispatchers.Main) {
                view.showMessage("Show successfully saved")
            }
        }
    }

    override fun removeShow(show: hu.bme.aut.tvshows.ui.model.Show) {
        launch {
            dbInteractor.removeTvShow(Show(
                id = show.id,
                name = show.name,
                premier = show.premier,
                genres = show.genres,
                summary = show.summary,
                imageUrl = show.imageUrl
            ))
        }
    }

    override fun search(keywords: String) {
        launch {
            val searchResult = networkInteractor.searchShows(keywords)
            val favouriteIds = dbInteractor.getFavouriteTvShows().map { it.id }
            val result = searchResult.map {
                hu.bme.aut.tvshows.ui.model.Show(
                    it.show.id,
                    it.show.name,
                    it.show.premiered,
                    it.show.genres.joinToString(", "),
                    it.show.summary?.stripHtml(),
                    it.show.image?.original,
                    favouriteIds.contains(it.show.id)
                )
            }
            withContext(Dispatchers.Main) {
                view.onSearchResults(result)
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