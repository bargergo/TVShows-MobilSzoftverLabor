package hu.bme.aut.tvshows.di

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import hu.bme.aut.tvshows.ui.edittvshow.EditTvShowContract
import hu.bme.aut.tvshows.ui.edittvshow.EditTvShowFragment
import hu.bme.aut.tvshows.ui.edittvshow.EditTvShowPresenter

@InstallIn(FragmentComponent::class)
@Module
abstract class EditTvShowModule {

    @Binds
    abstract fun provideView(impl: EditTvShowFragment): EditTvShowContract.View

    @Binds
    abstract fun providePresenter(impl: EditTvShowPresenter): EditTvShowContract.Presenter

}

@Module
@InstallIn(FragmentComponent::class)
object EditTvShowFragmentModule {
    @Provides
    fun provideFragment(fragment: Fragment) =
        fragment as EditTvShowFragment
}