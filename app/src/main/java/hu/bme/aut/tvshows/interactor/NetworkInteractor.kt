package hu.bme.aut.tvshows.interactor

interface NetworkInteractor {

    fun search(keywords: String): String
}