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
        id: Int,
        includeSeasons: Boolean,
        includeCast: Boolean
    ): ShowDetails {
        val seasons = if (includeSeasons) "seasons" else null
        val cast = if (includeCast) "cast" else null
        return showsApi.getShowsWithSeasonsAndCast(id, seasons, cast)
    }

    override suspend fun updateShow(id: Int, data: ShowData) {
        showsApi.putShowsShowId(id, data)
    }

    override suspend fun deleteShow(id: Int) {
        showsApi.deleteShowsShowId(id)
    }

    override suspend fun createShow(data: ShowData) {
        showsApi.postShows(data)
    }

    override suspend fun getEpisodes(seasonId: Int): List<Episode> {
        return episodesApi.getEpisodesForSeason(seasonId)
    }

    override suspend fun createEpisode(seasonId: Int, data: EpisodeData) {
        episodesApi.postSeasonsSeasonIdEpisodes(seasonId, data)
    }

    override suspend fun getEpisode(episodeId: Int): Episode {
        return episodesApi.getEpisodesEpisodeId(episodeId)
    }

    override suspend fun updateEpisode(id: Int, data: EpisodeData) {
        episodesApi.putEpisodesEpisodeId(id, data)
    }

    override suspend fun deleteEpisode(id: Int) {
        episodesApi.deleteEpisodesEpisodeId(id)
    }

    override suspend fun getSeasons(showId: Int): List<Season> {
        return seasonsApi.getSeasonsForShow(showId)
    }

    override suspend fun createSeason(showId: Int, data: SeasonData) {
        seasonsApi.postShowsShowIdSeasons(showId, data)
    }

    override suspend fun getSeason(seasonId: Int): Season {
        return seasonsApi.getSeasonsShowId(seasonId)
    }

    override suspend fun updateSeason(seasonId: Int, data: SeasonData) {
        seasonsApi.putSeasonsSeasonId(seasonId, data)
    }

    override suspend fun deleteSeason(seasonId: Int) {
        seasonsApi.deleteSeasonsSeasonId(seasonId)
    }

    override suspend fun getCast(showId: Int): List<Cast> {
        return castApi.getCastForShow(showId)
    }

    override suspend fun updateCast(showId: Int, data: List<CastData>) {
        castApi.postShowsShowIdCast(showId, data)
    }

    override suspend fun getPerson(id: Int): Person {
        return peopleApi.getPeoplePersonId(id)
    }

    override suspend fun updatePerson(id: Int, data: PersonData) {
        peopleApi.putPeoplePersonId(id, data)
    }

    override suspend fun deletePerson(id: Int) {
        peopleApi.deletePeoplePersonId(id)
    }

    override suspend fun createPerson(data: PersonData) {
        peopleApi.postPeople(data)
    }

    override suspend fun searchPeople(keywords: String): List<PersonSearchResult> {
        return peopleApi.getSearchPeople(keywords)
    }
}