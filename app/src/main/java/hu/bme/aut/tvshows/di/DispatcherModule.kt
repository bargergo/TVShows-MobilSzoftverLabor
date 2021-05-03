package hu.bme.aut.tvshows.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.tvshows.dispatchers.DefaultDispatcherProvider
import hu.bme.aut.tvshows.dispatchers.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
object DispatcherModule {

    @Provides
    fun providesMainDispatcher(): DispatcherProvider = DefaultDispatcherProvider()
}