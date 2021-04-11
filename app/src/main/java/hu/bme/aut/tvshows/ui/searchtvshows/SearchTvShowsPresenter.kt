package hu.bme.aut.tvshows.ui.searchtvshows

import hu.bme.aut.tvshows.interactor.NetworkInteractor
import javax.inject.Inject

class SearchTvShowsPresenter @Inject constructor(
    private val view: SearchTvShowsContract.View,
    private val networkInteractor: NetworkInteractor
) : SearchTvShowsContract.Presenter {

    override fun search(keywords: String) {
        view.onSearchResults(networkInteractor.search(keywords))
    }
}