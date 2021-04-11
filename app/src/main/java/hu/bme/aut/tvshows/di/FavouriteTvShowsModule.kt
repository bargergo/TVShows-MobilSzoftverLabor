package hu.bme.aut.tvshows.di

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import hu.bme.aut.tvshows.ui.favouritetvshows.FavouriteTvShowsContract
import hu.bme.aut.tvshows.ui.favouritetvshows.FavouriteTvShowsFragment
import hu.bme.aut.tvshows.ui.favouritetvshows.FavouriteTvShowsPresenter

@InstallIn(FragmentComponent::class)
@Module
abstract class FavouriteTvShowsModule {

    @Binds
    abstract fun provideView(impl: FavouriteTvShowsFragment): FavouriteTvShowsContract.View

    @Binds
    abstract fun providePresenter(impl: FavouriteTvShowsPresenter): FavouriteTvShowsContract.Presenter

}

@Module
@InstallIn(FragmentComponent::class)
object FavouriteTvShowsFragmentModule {
    @Provides
    fun provideFragment(fragment: Fragment) =
        fragment as FavouriteTvShowsFragment
}