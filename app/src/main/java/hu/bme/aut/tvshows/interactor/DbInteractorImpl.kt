package hu.bme.aut.tvshows.interactor

import hu.bme.aut.tvshows.data.*
import javax.inject.Inject

class DbInteractorImpl @Inject constructor(
    val showDao: ShowDAO,
    val seasonDAO: SeasonDAO
) : DbInteractor {

    override suspend fun insertTvShow(data: Show, seasons: List<Season>) {
        val showId = showDao.insertShow(data)
        for (season in seasons) {
            season.showId = showId
        }
        seasonDAO.insertSeason(*seasons.toTypedArray())
    }

    override suspend fun getFavouriteTvShows(): List<ShowWithSeasons> {
        return showDao.getShowsWithSeasons()
    }
}