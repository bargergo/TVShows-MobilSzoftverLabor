package hu.bme.aut.tvshows.di

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import hu.bme.aut.tvshows.ui.createtvshow.CreateTvShowFragment
import hu.bme.aut.tvshows.ui.tvshowdetail.TvShowDetailContract
import hu.bme.aut.tvshows.ui.tvshowdetail.TvShowDetailFragment
import hu.bme.aut.tvshows.ui.tvshowdetail.TvShowDetailPresenter

@InstallIn(FragmentComponent::class)
@Module
abstract class TvShowDetailModule {
    @Binds
    abstract fun provideView(impl: TvShowDetailFragment): TvShowDetailContract.View

    @Binds
    abstract fun providePresenter(impl: TvShowDetailPresenter): TvShowDetailContract.Presenter
}

@Module
@InstallIn(FragmentComponent::class)
object TvShowDetailFragmentModule {
    @Provides
    fun provideFragment(fragment: Fragment) =
        fragment as TvShowDetailFragment
}