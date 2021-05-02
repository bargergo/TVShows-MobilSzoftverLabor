package hu.bme.aut.tvshows.mock

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.tvshows.interactor.DbInteractor

@InstallIn(SingletonComponent::class)
@Module
abstract class FakeDbInteractorModule {

    @Binds
    abstract fun bindDataSource(
        impl: FakeDbInteractorImpl
    ): DbInteractor

}