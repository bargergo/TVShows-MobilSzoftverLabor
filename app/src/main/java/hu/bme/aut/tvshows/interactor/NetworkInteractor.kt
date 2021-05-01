package hu.bme.aut.tvshows.interactor

import hu.bme.aut.tvshows.model.*

interface NetworkInteractor {

    suspend fun searchShows(keywords: String): List<ShowSearchResult>
    suspend fun getShow(id: Int, includeSeasons: Boolean = false, includeCast: Boolean = false): ShowDetails
    suspend fun updateShow(id: Int, data: ShowData)
    suspend fun deleteShow(id: Int)
    suspend fun createShow(data: ShowData)

    suspend fun getEpisodes(seasonId: Int): List<Episode>
    suspend fun createEpisode(seasonId: Int, data: EpisodeData)
    suspend fun getEpisode(episodeId: Int): Episode
    suspend fun updateEpisode(id: Int, data: EpisodeData)
    suspend fun deleteEpisode(id: Int)

    suspend fun getSeasons(showId: Int): List<Season>
    suspend fun createSeason(showId: Int, data: SeasonData)
    suspend fun getSeason(seasonId: Int): Season
    suspend fun updateSeason(seasonId: Int, data: SeasonData)
    suspend fun deleteSeason(seasonId: Int)

    suspend fun getCast(showId: Int): List<Cast>
    suspend fun updateCast(showId: Int, data: List<CastData>)

    suspend fun getPerson(id: Int): Person
    suspend fun updatePerson(id: Int, data: PersonData)
    suspend fun deletePerson(id: Int)
    suspend fun createPerson(data: PersonData)
    suspend fun searchPeople(keywords: String): List<PersonSearchResult>
}