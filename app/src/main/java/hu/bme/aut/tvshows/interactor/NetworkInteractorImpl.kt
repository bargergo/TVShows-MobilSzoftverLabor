package hu.bme.aut.tvshows.interactor

import hu.bme.aut.tvshows.model.ShowData
import hu.bme.aut.tvshows.model.ShowSearchResult
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

    override suspend fun createShow(data: ShowData) {
        showsApi.postShows(data)
    }
}