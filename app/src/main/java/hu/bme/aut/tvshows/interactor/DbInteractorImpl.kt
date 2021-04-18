package hu.bme.aut.tvshows.interactor

import hu.bme.aut.tvshows.data.Show
import hu.bme.aut.tvshows.data.ShowDAO
import javax.inject.Inject

class DbInteractorImpl @Inject constructor(
    val dao: ShowDAO
) : DbInteractor {

    override suspend fun insertTvShow(data: Show) {
        dao.insertShow(data)
    }

    override suspend fun getFavouriteTvShows(): List<Show> {
        return dao.getShows()
    }
}