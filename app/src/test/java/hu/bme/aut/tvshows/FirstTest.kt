package hu.bme.aut.tvshows

import android.os.Looper.getMainLooper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.tvshows.di.*
import hu.bme.aut.tvshows.interactor.DbInteractor
import hu.bme.aut.tvshows.interactor.NetworkInteractor
import hu.bme.aut.tvshows.model.*
import hu.bme.aut.tvshows.ui.model.Show
import hu.bme.aut.tvshows.ui.searchtvshows.SearchTvShowsContract
import hu.bme.aut.tvshows.ui.searchtvshows.SearchTvShowsFragment
import hu.bme.aut.tvshows.ui.searchtvshows.SearchTvShowsPresenter
import hu.bme.aut.tvshows.utils.CoroutineTestRule
import hu.bme.aut.tvshows.utils.mock
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.mockito.Mockito.times
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class, manifest=Config.NONE)
@UninstallModules(ProductionModule::class, SearchTvShowsModule::class, NetworkInteractorModule::class, DbInteractorModule::class)
class FirstTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Inject
    lateinit var someString: String

    @Inject
    lateinit var dbInteractor: DbInteractor
    @Inject
    lateinit var networkInteractor: NetworkInteractor
    private lateinit var searchTvShowsView: SearchTvShowsContract.View

    private val testDispatcher = coroutineTestRule.testDispatcher
    private val testDispatcherProvider = coroutineTestRule.testDispatcherProvider


    @Before
    fun init() {
        hiltRule.inject()
        searchTvShowsView = mock()
    }


    @Test
    fun hiltTest(){
        assertThat(someString, containsString("TEST string"))
    }

    @Test
    fun testSearch() = testDispatcher.runBlockingTest  {
        val searchTvShowsPresenter = SearchTvShowsPresenter(searchTvShowsView,networkInteractor,dbInteractor,testDispatcherProvider)
        searchTvShowsPresenter.search("Test")
        Mockito.verify(searchTvShowsView).onSearchResults(listOf(
            Show(
                1,
                "Under the Dome",
                LocalDate.parse("2013-06-24"),
                "Drama, Science-Fiction, Thriller",
                "Under the Dome is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.\n\n",
                "https://static.tvmaze.com/uploads/images/original_untouched/81/202627.jpg",
                false
            )))
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class SearchTvShowsModule {

        @Singleton
        @Binds
        abstract fun providePresenter(impl: SearchTvShowsPresenter): SearchTvShowsContract.Presenter
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object SearchTvShowsViewModule {

        @Singleton
        @Provides
        fun provideView(): SearchTvShowsContract.View = mock()
    }




    @Module
    @InstallIn(SingletonComponent::class)
    object ProductionModule {


        @Singleton
        @Provides
        fun provideString(): String{
            return "This is a TEST string I'm providing for injection"
        }
    }
}