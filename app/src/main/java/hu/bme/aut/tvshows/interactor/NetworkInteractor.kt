package hu.bme.aut.tvshows.interactor

import hu.bme.aut.tvshows.model.ShowData
import hu.bme.aut.tvshows.model.ShowSearchResult

interface NetworkInteractor {

    suspend fun searchShows(keywords: String): List<ShowSearchResult>
    suspend fun createShow(data: ShowData)
}