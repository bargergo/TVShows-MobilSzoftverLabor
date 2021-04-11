package hu.bme.aut.tvshows.ui.createtvshow

import hu.bme.aut.tvshows.interactor.DbInteractor
import javax.inject.Inject

class CreateTvShowPresenter @Inject constructor(
    private val view: CreateTvShowContract.View,
    private val dbInteractor: DbInteractor
) : CreateTvShowContract.Presenter {

    override fun onCreateTvShow(data: String) {
        dbInteractor.insertTvShow(data)
        view.showMessage("Successfully created TV Show")
    }
}