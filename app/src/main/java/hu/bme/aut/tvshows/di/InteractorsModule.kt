package hu.bme.aut.tvshows.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import hu.bme.aut.tvshows.interactor.DbInteractor
import hu.bme.aut.tvshows.interactor.DbInteractorImpl
import hu.bme.aut.tvshows.interactor.NetworkInteractor
import hu.bme.aut.tvshows.interactor.NetworkInteractorImpl

@InstallIn(FragmentComponent::class)
@Module
abstract class DbInteractorModule {

    @Binds
    abstract fun bindDataSource(
        impl: DbInteractorImpl
    ): DbInteractor

}

@InstallIn(FragmentComponent::class)
@Module
abstract class NetworkInteractorModule {

    @Binds
    abstract fun bindDataSource(
        impl: NetworkInteractorImpl
    ): NetworkInteractor

}