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
import hu.bme.aut.tvshows.mock.FakeNetworkInteractor
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
@UninstallModules(SearchTvShowsModule::class, NetworkInteractorModule::class, DbInteractorModule::class)
class FirstTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Inject
    lateinit var dbInteractor: DbInteractor
    private lateinit var searchTvShowsView: SearchTvShowsContract.View

    private val testDispatcher = coroutineTestRule.testDispatcher
    private val testDispatcherProvider = coroutineTestRule.testDispatcherProvider


    @Before
    fun init() {
        hiltRule.inject()
        searchTvShowsView = mock()
    }

    @Test
    fun testSearch() = testDispatcher.runBlockingTest  {

        val networkInteractor = FakeNetworkInteractor()
        networkInteractor.searchResults = listOf(
                ShowSearchResult(
                        50.1f,
                        ShowSummary(
                                1,
                                "https://www.tvmaze.com/shows/1/under-the-dome",
                                "Under the Dome",
                                "Scripted",
                                "English",
                                listOf("Drama",
                                        "Science-Fiction",
                                        "Thriller"),
                                "Ended",
                                60,
                                LocalDate.parse("2013-06-24", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                "http://www.cbs.com/shows/under-the-dome/",
                                Schedule(
                                        "22:00",
                                        listOf("Thursday")
                                ),
                                Rating(6.6f),
                                96,
                                Network(
                                        2,
                                        "CBS",
                                        Country(
                                                "United States",
                                                "US",
                                                "America/New_York"
                                        )
                                ),
                                null,
                                null,
                                Externals(25988, 264492, "tt1553656"),
                                Image(
                                        "https://static.tvmaze.com/uploads/images/medium_portrait/81/202627.jpg",
                                        "https://static.tvmaze.com/uploads/images/original_untouched/81/202627.jpg"
                                ),
                                "<p><b>Under the Dome</b> is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.</p>",
                                1617697381,
                                Links(
                                        LinksSelf(
                                                "https://api.tvmaze.com/shows/1"
                                        ),
                                        LinksSelf(
                                                "https://api.tvmaze.com/episodes/185054"
                                        )
                                )
                        )
                )
        )

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
}