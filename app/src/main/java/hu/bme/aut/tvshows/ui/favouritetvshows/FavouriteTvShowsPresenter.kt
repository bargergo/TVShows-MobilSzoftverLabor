package hu.bme.aut.tvshows.ui.favouritetvshows

import hu.bme.aut.tvshows.interactor.DbInteractor
import javax.inject.Inject

class FavouriteTvShowsPresenter @Inject constructor(
    private val view: FavouriteTvShowsContract.View,
    private val dbInteractor: DbInteractor
) : FavouriteTvShowsContract.Presenter {

    override fun getFavouriteTvShows() {
        view.updateView(dbInteractor.getFavouriteTvShows())
    }
}