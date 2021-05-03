package hu.bme.aut.tvshows.mock

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.tvshows.interactor.NetworkInteractor

@InstallIn(SingletonComponent::class)
@Module
abstract class FakeNetworkInteractorModule {

    @Binds
    abstract fun bindDataSource(
        impl: FakeNetworkInteractor
    ): NetworkInteractor

}