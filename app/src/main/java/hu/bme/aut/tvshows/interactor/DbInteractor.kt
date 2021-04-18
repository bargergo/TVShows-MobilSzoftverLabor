package hu.bme.aut.tvshows.interactor

import hu.bme.aut.tvshows.data.Show

interface DbInteractor {

    suspend fun insertTvShow(data: Show)
    suspend fun getFavouriteTvShows(): List<Show>
}
