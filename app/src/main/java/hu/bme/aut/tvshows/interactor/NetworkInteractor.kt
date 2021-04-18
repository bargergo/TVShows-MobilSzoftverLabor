package hu.bme.aut.tvshows.interactor

interface NetworkInteractor {

    suspend fun search(keywords: String): String
}