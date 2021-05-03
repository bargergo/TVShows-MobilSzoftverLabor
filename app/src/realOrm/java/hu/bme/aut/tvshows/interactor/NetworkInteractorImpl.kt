package hu.bme.aut.tvshows.interactor

import hu.bme.aut.tvshows.model.*
import hu.bme.aut.tvshows.network.api.*
import retrofit2.Retrofit
import javax.inject.Inject

class NetworkInteractorImpl @Inject constructor(retrofit: Retrofit) : NetworkInteractor {
    private val castApi: CastApi = retrofit.create(CastApi::class.java)
    private val episodesApi: EpisodesApi = retrofit.create(EpisodesApi::class.java)
    private val peopleApi: PeopleApi = retrofit.create(PeopleApi::class.java)
    private val seasonsApi: SeasonsApi = retrofit.create(SeasonsApi::class.java)
    private val showsApi: ShowsApi = retrofit.create(ShowsApi::class.java)

    override suspend fun searchShows(keywords: String): List<ShowSearchResult> {
        return showsApi.getSearchShows(keywords)
    }

    override suspend fun getShow(
        id: Long,
        includeSeasons: Boolean,
        includeCast: Boolean
    ): ShowDetails {
        val seasons = if (includeSeasons) "seasons" else null
        val cast = if (includeCast) "cast" else null
        return showsApi.getShowsWithSeasonsAndCast(id, seasons, cast)
    }

    override suspend fun updateShow(id: Long, data: ShowData) {
        showsApi.putShowsShowId(id, data)
    }

    override suspend fun deleteShow(id: Long) {
        showsApi.deleteShowsShowId(id)
    }

    override suspend fun createShow(data: ShowData) {
        showsApi.postShows(data)
    }

    override suspend fun getEpisodes(seasonId: Long): List<Episode> {
        return episodesApi.getEpisodesForSeason(seasonId)
    }

    override suspend fun createEpisode(seasonId: Long, data: EpisodeData) {
        episodesApi.postSeasonsSeasonIdEpisodes(seasonId, data)
    }

    override suspend fun getEpisode(episodeId: Long): Episode {
        return episodesApi.getEpisodesEpisodeId(episodeId)
    }

    override suspend fun updateEpisode(id: Long, data: EpisodeData) {
        episodesApi.putEpisodesEpisodeId(id, data)
    }

    override suspend fun deleteEpisode(id: Long) {
        episodesApi.deleteEpisodesEpisodeId(id)
    }

    override suspend fun getSeasons(showId: Long): List<Season> {
        return seasonsApi.getSeasonsForShow(showId)
    }

    override suspend fun createSeason(showId: Long, data: SeasonData) {
        seasonsApi.postShowsShowIdSeasons(showId, data)
    }

    override suspend fun getSeason(seasonId: Long): Season {
        return seasonsApi.getSeasonsShowId(seasonId)
    }

    override suspend fun updateSeason(seasonId: Long, data: SeasonData) {
        seasonsApi.putSeasonsSeasonId(seasonId, data)
    }

    override suspend fun deleteSeason(seasonId: Long) {
        seasonsApi.deleteSeasonsSeasonId(seasonId)
    }

    override suspend fun getCast(showId: Long): List<Cast> {
        return castApi.getCastForShow(showId)
    }

    override suspend fun updateCast(showId: Long, data: List<CastData>) {
        castApi.postShowsShowIdCast(showId, data)
    }

    override suspend fun getPerson(id: Long): Person {
        return peopleApi.getPeoplePersonId(id)
    }

    override suspend fun updatePerson(id: Long, data: PersonData) {
        peopleApi.putPeoplePersonId(id, data)
    }

    override suspend fun deletePerson(id: Long) {
        peopleApi.deletePeoplePersonId(id)
    }

    override suspend fun createPerson(data: PersonData) {
        peopleApi.postPeople(data)
    }

    override suspend fun searchPeople(keywords: String): List<PersonSearchResult> {
        return peopleApi.getSearchPeople(keywords)
    }
}