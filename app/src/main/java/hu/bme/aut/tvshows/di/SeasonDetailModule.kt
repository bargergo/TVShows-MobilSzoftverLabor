package hu.bme.aut.tvshows.di

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import hu.bme.aut.tvshows.ui.seasondetail.SeasonDetailContract
import hu.bme.aut.tvshows.ui.seasondetail.SeasonDetailFragment
import hu.bme.aut.tvshows.ui.seasondetail.SeasonDetailPresenter

@InstallIn(FragmentComponent::class)
@Module
abstract class SeasonDetailModule {
    @Binds
    abstract fun provideView(impl: SeasonDetailFragment): SeasonDetailContract.View

    @Binds
    abstract fun providePresenter(impl: SeasonDetailPresenter): SeasonDetailContract.Presenter
}

@Module
@InstallIn(FragmentComponent::class)
object SeasonDetailFragmentModule {
    @Provides
    fun provideFragment(fragment: Fragment) =
            fragment as SeasonDetailFragment
}