package hu.bme.aut.tvshows.di

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import hu.bme.aut.tvshows.ui.episodedetail.EpisodeDetailContract
import hu.bme.aut.tvshows.ui.episodedetail.EpisodeDetailFragment
import hu.bme.aut.tvshows.ui.episodedetail.EpisodeDetailPresenter

@InstallIn(FragmentComponent::class)
@Module
abstract class EpisodeDetailModule {
    @Binds
    abstract fun provideView(impl: EpisodeDetailFragment): EpisodeDetailContract.View

    @Binds
    abstract fun providePresenter(impl: EpisodeDetailPresenter): EpisodeDetailContract.Presenter
}

@Module
@InstallIn(FragmentComponent::class)
object EpisodeDetailFragmentModule {
    @Provides
    fun provideFragment(fragment: Fragment) =
            fragment as EpisodeDetailFragment
}