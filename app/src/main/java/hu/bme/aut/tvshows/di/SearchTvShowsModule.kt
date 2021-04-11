package hu.bme.aut.tvshows.di

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import hu.bme.aut.tvshows.ui.searchtvshows.SearchTvShowsContract
import hu.bme.aut.tvshows.ui.searchtvshows.SearchTvShowsFragment
import hu.bme.aut.tvshows.ui.searchtvshows.SearchTvShowsPresenter

@InstallIn(FragmentComponent::class)
@Module
abstract class SearchTvShowsModule {

    @Binds
    abstract fun provideView(impl: SearchTvShowsFragment): SearchTvShowsContract.View

    @Binds
    abstract fun providePresenter(impl: SearchTvShowsPresenter): SearchTvShowsContract.Presenter

}

@Module
@InstallIn(FragmentComponent::class)
object SearchTvShowsFragmentModule {
    @Provides
    fun provideFragment(fragment: Fragment) =
        fragment as SearchTvShowsFragment
}