package hu.bme.aut.tvshows.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.tvshows.network.ApiClient
import org.threeten.bp.format.DateTimeFormatter
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provide(): Retrofit {
        val apiClient = ApiClient().apply {
            createDefaultAdapter()
            setLocalDateFormat(DateTimeFormatter.ofPattern(("yyyy-MM-dd")))
        }
        return apiClient.adapterBuilder.build()
    }
}