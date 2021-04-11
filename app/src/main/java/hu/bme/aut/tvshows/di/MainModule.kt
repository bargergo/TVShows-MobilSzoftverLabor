package hu.bme.aut.tvshows.di

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import hu.bme.aut.tvshows.interactor.DbInteractor
import hu.bme.aut.tvshows.interactor.DbInteractorImpl
import hu.bme.aut.tvshows.ui.createtvshow.CreateTvShowContract
import hu.bme.aut.tvshows.ui.createtvshow.CreateTvShowFragment
import hu.bme.aut.tvshows.ui.createtvshow.CreateTvShowPresenter
import hu.bme.aut.tvshows.ui.favouritetvshows.FavouriteTvShowsContract
import hu.bme.aut.tvshows.ui.favouritetvshows.FavouriteTvShowsPresenter
import hu.bme.aut.tvshows.ui.favouritetvshows.FavouriteTvShowsFragment

@InstallIn(FragmentComponent::class)
@Module
abstract class CreateTvShowModule {

    @Binds
    abstract fun provideCreateTvShowView(impl: CreateTvShowFragment): CreateTvShowContract.View

    @Binds
    abstract fun provideCreateTvShowPresenter(impl: CreateTvShowPresenter): CreateTvShowContract.Presenter

}

@InstallIn(FragmentComponent::class)
@Module
abstract class FavouriteTvShowsModule {

    @Binds
    abstract fun provideFavouriteTvShowsView(impl: FavouriteTvShowsFragment): FavouriteTvShowsContract.View

    @Binds
    abstract fun provideFavouriteTvShowsPresenter(impl: FavouriteTvShowsPresenter): FavouriteTvShowsContract.Presenter

}

@InstallIn(FragmentComponent::class)
@Module
abstract class DbModule {

    @Binds
    abstract fun bindDataSource(
        impl: DbInteractorImpl
    ): DbInteractor

}

@Module
@InstallIn(FragmentComponent::class)
object FavouriteTvShowsFragmentModule {
    @Provides
    fun provideFragment(fragment: Fragment) =
        fragment as FavouriteTvShowsFragment
}



@Module
@InstallIn(FragmentComponent::class)
object CreateTvShowFragmentModule {
    @Provides
    fun provideFragment(fragment: Fragment) =
        fragment as CreateTvShowFragment
}