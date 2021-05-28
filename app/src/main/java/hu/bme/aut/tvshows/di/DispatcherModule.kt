package hu.bme.aut.tvshows.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.tvshows.dispatchers.DefaultDispatcherProvider
import hu.bme.aut.tvshows.dispatchers.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DispatcherModule {

    @Provides
    @Singleton
    fun providesMainDispatcher(): DispatcherProvider = DefaultDispatcherProvider()
}