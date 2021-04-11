package hu.bme.aut.tvshows.ui.createtvshow

interface CreateTvShowContract {

    interface View {
        fun showMessage(message: String)
    }

    interface Presenter {
        fun onCreateTvShow(data: String)
    }
}