package hu.bme.aut.tvshows.interactor

import hu.bme.aut.tvshows.data.Cast
import hu.bme.aut.tvshows.data.Episode
import hu.bme.aut.tvshows.data.Season
import hu.bme.aut.tvshows.data.Show

interface DbInteractor {

    suspend fun insertTvShow(data: Show, seasons: List<Season>, episodes: List<Episode>, cast: List<Cast>)
    suspend fun removeTvShow(data: Show)
    suspend fun getFavouriteTvShows(): List<Show>
}
