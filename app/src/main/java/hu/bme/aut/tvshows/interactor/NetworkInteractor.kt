package hu.bme.aut.tvshows.interactor

import hu.bme.aut.tvshows.model.*

interface NetworkInteractor {

    suspend fun searchShows(keywords: String): List<ShowSearchResult>
    suspend fun getShow(id: Long, includeSeasons: Boolean = false, includeCast: Boolean = false): ShowDetails
    suspend fun updateShow(id: Long, data: ShowData)
    suspend fun deleteShow(id: Long)
    suspend fun createShow(data: ShowData)

    suspend fun getEpisodes(seasonId: Long): List<Episode>
    suspend fun createEpisode(seasonId: Long, data: EpisodeData)
    suspend fun getEpisode(episodeId: Long): Episode
    suspend fun updateEpisode(id: Long, data: EpisodeData)
    suspend fun deleteEpisode(id: Long)

    suspend fun getSeasons(showId: Long): List<Season>
    suspend fun createSeason(showId: Long, data: SeasonData)
    suspend fun getSeason(seasonId: Long): Season
    suspend fun updateSeason(seasonId: Long, data: SeasonData)
    suspend fun deleteSeason(seasonId: Long)

    suspend fun getCast(showId: Long): List<Cast>
    suspend fun updateCast(showId: Long, data: List<CastData>)

    suspend fun getPerson(id: Long): Person
    suspend fun updatePerson(id: Long, data: PersonData)
    suspend fun deletePerson(id: Long)
    suspend fun createPerson(data: PersonData)
    suspend fun searchPeople(keywords: String): List<PersonSearchResult>
}