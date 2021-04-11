package hu.bme.aut.tvshows.interactor

import javax.inject.Inject

class NetworkInteractorImpl @Inject constructor() : NetworkInteractor {

    override fun search(keywords: String): String {
        return "No results found."
    }
}