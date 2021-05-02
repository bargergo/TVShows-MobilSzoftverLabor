package hu.bme.aut.tvshows

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.tvshows.di.ProductionModule
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject
import javax.inject.Singleton

@RunWith(RobolectricTestRunner::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class, manifest=Config.NONE)
@UninstallModules(ProductionModule::class)
class FirstTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var someString: String

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun hiltTest(){
        assertThat(someString, containsString("TEST string"))
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