package hu.bme.aut.tvshows.interactor

import hu.bme.aut.tvshows.model.ShowData

interface NetworkInteractor {

    suspend fun search(keywords: String): String
    suspend fun createShow(data: ShowData)
}