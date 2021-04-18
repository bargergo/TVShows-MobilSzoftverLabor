package hu.bme.aut.tvshows.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.tvshows.data.AppDatabase
import hu.bme.aut.tvshows.data.SeasonDAO
import hu.bme.aut.tvshows.data.ShowDAO
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DbModule {

    @Provides
    fun provideShowDao(appDatabase: AppDatabase): ShowDAO {
        return appDatabase.showDao()
    }

    @Provides
    fun provideSeasonDao(appDatabase: AppDatabase): SeasonDAO {
        return appDatabase.seasonDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }
}