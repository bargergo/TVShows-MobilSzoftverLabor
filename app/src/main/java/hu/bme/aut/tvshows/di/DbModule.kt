package hu.bme.aut.tvshows.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.tvshows.data.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DbModule {

    @Provides
    @Singleton
    fun provideEpisodeDao(appDatabase: AppDatabase): EpisodeDAO {
        return appDatabase.episodeDao()
    }

    @Provides
    @Singleton
    fun provideCastDao(appDatabase: AppDatabase): CastDao {
        return appDatabase.castDao()
    }

    @Provides
    @Singleton
    fun provideShowDao(appDatabase: AppDatabase): ShowDAO {
        return appDatabase.showDao()
    }

    @Provides
    @Singleton
    fun provideSeasonDao(appDatabase: AppDatabase): SeasonDAO {
        return appDatabase.seasonDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }
}