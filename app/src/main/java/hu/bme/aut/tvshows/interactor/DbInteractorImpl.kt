package hu.bme.aut.tvshows.interactor

import hu.bme.aut.tvshows.data.*
import javax.inject.Inject

class DbInteractorImpl @Inject constructor(
    val showDao: ShowDAO,
    val seasonDao: SeasonDAO,
    val episodeDao: EpisodeDAO,
    val castDao: CastDao
) : DbInteractor {

    override suspend fun insertTvShow(data: Show, seasons: List<Season>, episodes: List<Episode>, cast: List<Cast>) {
        showDao.insertShow(data)
        seasonDao.insertSeason(*seasons.toTypedArray())
        episodeDao.insertEpisode(*episodes.toTypedArray())
        castDao.insertCast(*cast.toTypedArray())
    }

    override suspend fun getFavouriteTvShows(): List<Show> {
        return showDao.getShows()
    }
}