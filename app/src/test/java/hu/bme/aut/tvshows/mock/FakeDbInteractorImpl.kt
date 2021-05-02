package hu.bme.aut.tvshows.mock

import hu.bme.aut.tvshows.data.*
import hu.bme.aut.tvshows.interactor.DbInteractor
import hu.bme.aut.tvshows.model.ShowData
import javax.inject.Inject

class FakeDbInteractorImpl @Inject constructor() : DbInteractor {

    override suspend fun insertTvShow(data: Show, seasons: List<Season>, episodes: List<Episode>, cast: List<Cast>) {
    }

    override suspend fun removeTvShow(data: Show) {
    }

    override suspend fun getFavouriteTvShows(): List<Show> {
        return emptyList()
    }

    override suspend fun getFavouriteTvShowIds(): List<Long> {
        return emptyList()
    }

    override suspend fun getShow(id: Long): ShowWithSeasonsAndEpisodesAndCast? {
        return null
    }

    override suspend fun getEpisodesForSeason(seasonId: Long): List<Episode> {
        return emptyList()
    }

    override suspend fun getEpisode(id: Long): Episode {
        TODO()
    }

    override suspend fun updateShow(data: Show) {
        TODO()
    }
}