package hu.bme.aut.tvshows.interactor

import hu.bme.aut.tvshows.data.*

interface DbInteractor {

    suspend fun insertTvShow(data: Show, seasons: List<Season>, episodes: List<Episode>, cast: List<Cast>)
    suspend fun removeTvShow(data: Show)
    suspend fun getFavouriteTvShows(): List<Show>
    suspend fun getFavouriteTvShowIds(): List<Long>
    suspend fun getShow(id: Long): ShowWithSeasonsAndEpisodesAndCast?
    suspend fun getEpisodesForSeason(seasonId: Long): List<Episode>
    suspend fun getEpisode(id: Long): Episode
}
