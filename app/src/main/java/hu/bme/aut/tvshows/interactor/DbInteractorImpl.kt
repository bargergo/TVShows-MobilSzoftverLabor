package hu.bme.aut.tvshows.interactor

import javax.inject.Inject

class DbInteractorImpl @Inject constructor() : DbInteractor {

    override fun insertTvShow(data: String) {

    }

    override fun getFavouriteTvShows(): String {
        return """
            Friends,
            The Big Bang Theory,
            Young Sheldon
        """.trimIndent()
    }
}