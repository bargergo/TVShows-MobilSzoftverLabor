package hu.bme.aut.tvshows.ui.searchtvshows

import hu.bme.aut.tvshows.data.Cast
import hu.bme.aut.tvshows.data.Episode
import hu.bme.aut.tvshows.data.Season
import hu.bme.aut.tvshows.data.Show
import hu.bme.aut.tvshows.interactor.DbInteractor
import hu.bme.aut.tvshows.interactor.NetworkInteractor
import hu.bme.aut.tvshows.model.ShowSummary
import hu.bme.aut.tvshows.util.stripHtml
import kotlinx.coroutines.*
import javax.inject.Inject

class SearchTvShowsPresenter @Inject constructor(
    private val view: SearchTvShowsContract.View,
    private val networkInteractor: NetworkInteractor,
    private val dbInteractor: DbInteractor
) : SearchTvShowsContract.Presenter, CoroutineScope by MainScope() {

    override fun saveShow(show: ShowSummary) {
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
                    premier = show.premiered,
                    genres = show.genres.joinToString(", "),
                    summary = show.summary?.stripHtml(),
                    imageUrl = show.image?.original
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

    override fun search(keywords: String) {
        launch {
            val result = networkInteractor.searchShows(keywords)
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