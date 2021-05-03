package hu.bme.aut.tvshows.interactor

import hu.bme.aut.tvshows.data.*
import hu.bme.aut.tvshows.model.ShowData
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

    override suspend fun removeTvShow(data: Show) {
        val showId = data.id!!
        castDao.deleteCastForShow(showId)
        val seasonIds = seasonDao.getSeasonsForSeries(showId).map { it.id!! }
        episodeDao.deleteEpisodesForSeasons(seasonIds)
        seasonDao.deleteSeasonsForShow(showId)
        showDao.deleteShow(data)
    }

    override suspend fun getFavouriteTvShows(): List<Show> {
        return showDao.getShows()
    }

    override suspend fun getFavouriteTvShowIds(): List<Long> {
        return showDao.getShowIds()
    }

    override suspend fun getShow(id: Long): ShowWithSeasonsAndEpisodesAndCast? {
        return showDao.getShowWithSeasonsAndEpisodesAndCast(id)
    }

    override suspend fun getEpisodesForSeason(seasonId: Long): List<Episode> {
        return episodeDao.getEpisodesForSeason(seasonId)
    }

    override suspend fun getEpisode(id: Long): Episode {
        return episodeDao.getEpisode(id)
    }

    override suspend fun updateShow(data: Show) {
        showDao.updateShow(data)
    }
}