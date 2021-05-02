package hu.bme.aut.tvshows.interactor

import hu.bme.aut.tvshows.data.Season
import hu.bme.aut.tvshows.data.Show

interface DbInteractor {

    suspend fun insertTvShow(data: Show, seasons: List<Season>)
    suspend fun getFavouriteTvShows(): List<Show>
}
