package hu.bme.aut.tvshows.di

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import hu.bme.aut.tvshows.interactor.DbInteractor
import hu.bme.aut.tvshows.interactor.DbInteractorImpl
import hu.bme.aut.tvshows.ui.createtvshow.CreateTvShowContract
import hu.bme.aut.tvshows.ui.createtvshow.CreateTvShowFragment
import hu.bme.aut.tvshows.ui.createtvshow.CreateTvShowPresenter

@InstallIn(FragmentComponent::class)
@Module
abstract class CreateTvShowModule {

    @Binds
    abstract fun bindFragment(fragment: CreateTvShowFragment): CreateTvShowContract.View

    @Binds
    abstract fun bindPresenter(impl: CreateTvShowPresenter): CreateTvShowContract.Presenter

}

@InstallIn(FragmentComponent::class)
@Module
abstract class DbModule {

    @Binds
    abstract fun bindDataSource(
        impl: DbInteractorImpl
    ): DbInteractor

}

@InstallIn(FragmentComponent::class)
@Module
object CreateTvShowFragmentModule {

    @Provides
    fun bindFragment(fragment: Fragment): CreateTvShowFragment {
        return fragment as CreateTvShowFragment
    }
}