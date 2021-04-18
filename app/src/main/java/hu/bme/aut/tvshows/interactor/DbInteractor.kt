package hu.bme.aut.tvshows.interactor

import hu.bme.aut.tvshows.data.Season
import hu.bme.aut.tvshows.data.Show
import hu.bme.aut.tvshows.data.ShowWithSeasons

interface DbInteractor {

    suspend fun insertTvShow(data: Show, seasons: List<Season>)
    suspend fun getFavouriteTvShows(): List<ShowWithSeasons>
}
