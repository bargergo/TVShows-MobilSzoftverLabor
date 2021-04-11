package hu.bme.aut.tvshows.di

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import hu.bme.aut.tvshows.interactor.DbInteractor
import hu.bme.aut.tvshows.interactor.DbInteractorImpl
import hu.bme.aut.tvshows.interactor.NetworkInteractor
import hu.bme.aut.tvshows.interactor.NetworkInteractorImpl
import hu.bme.aut.tvshows.ui.createtvshow.CreateTvShowContract
import hu.bme.aut.tvshows.ui.createtvshow.CreateTvShowFragment
import hu.bme.aut.tvshows.ui.createtvshow.CreateTvShowPresenter
import hu.bme.aut.tvshows.ui.favouritetvshows.FavouriteTvShowsContract
import hu.bme.aut.tvshows.ui.favouritetvshows.FavouriteTvShowsPresenter
import hu.bme.aut.tvshows.ui.favouritetvshows.FavouriteTvShowsFragment
import hu.bme.aut.tvshows.ui.searchtvshows.SearchTvShowsContract
import hu.bme.aut.tvshows.ui.searchtvshows.SearchTvShowsFragment
import hu.bme.aut.tvshows.ui.searchtvshows.SearchTvShowsPresenter

@InstallIn(FragmentComponent::class)
@Module
abstract class CreateTvShowModule {

    @Binds
    abstract fun provideView(impl: CreateTvShowFragment): CreateTvShowContract.View

    @Binds
    abstract fun providePresenter(impl: CreateTvShowPresenter): CreateTvShowContract.Presenter

}

@Module
@InstallIn(FragmentComponent::class)
object CreateTvShowFragmentModule {
    @Provides
    fun provideFragment(fragment: Fragment) =
        fragment as CreateTvShowFragment
}