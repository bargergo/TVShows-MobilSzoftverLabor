package hu.bme.aut.tvshows.mock

import hu.bme.aut.tvshows.interactor.NetworkInteractor
import hu.bme.aut.tvshows.model.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

class FakeNetworkInteractor @Inject constructor() : NetworkInteractor {

    var searchResults: List<ShowSearchResult> = emptyList()

    override suspend fun searchShows(keywords: String): List<ShowSearchResult> {
        return searchResults
    }

    override suspend fun getShow(
        id: Long,
        includeSeasons: Boolean,
        includeCast: Boolean
    ): ShowDetails {
        TODO("Not yet implemented")
    }

    override suspend fun updateShow(id: Long, data: ShowData) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteShow(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun createShow(data: ShowData) {

    }

    override suspend fun getEpisodes(seasonId: Long): List<Episode> {
        TODO("Not yet implemented")
    }

    override suspend fun createEpisode(seasonId: Long, data: EpisodeData) {
        TODO("Not yet implemented")
    }

    override suspend fun getEpisode(episodeId: Long): Episode {
        TODO("Not yet implemented")
    }

    override suspend fun updateEpisode(id: Long, data: EpisodeData) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEpisode(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun getSeasons(showId: Long): List<Season> {
        TODO("Not yet implemented")
    }

    override suspend fun createSeason(showId: Long, data: SeasonData) {
        TODO("Not yet implemented")
    }

    override suspend fun getSeason(seasonId: Long): Season {
        TODO("Not yet implemented")
    }

    override suspend fun updateSeason(seasonId: Long, data: SeasonData) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSeason(seasonId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun getCast(showId: Long): List<Cast> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCast(showId: Long, data: List<CastData>) {
        TODO("Not yet implemented")
    }

    override suspend fun getPerson(id: Long): Person {
        TODO("Not yet implemented")
    }

    override suspend fun updatePerson(id: Long, data: PersonData) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePerson(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun createPerson(data: PersonData) {
        TODO("Not yet implemented")
    }

    override suspend fun searchPeople(keywords: String): List<PersonSearchResult> {
        TODO("Not yet implemented")
    }
}