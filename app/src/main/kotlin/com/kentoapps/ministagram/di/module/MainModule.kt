package com.kentoapps.ministagram.di.module

import android.arch.lifecycle.ViewModel
import com.kentoapps.ministagram.di.ViewModelKey
import com.kentoapps.ministagram.ui.friends.FriendsFragment
import com.kentoapps.ministagram.ui.friends.FriendsViewModel
import com.kentoapps.ministagram.ui.timeline.TimelineFragment
import com.kentoapps.ministagram.ui.timeline.TimelineViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun contributeTimelineFragment(): TimelineFragment

    @Binds
    @IntoMap
    @ViewModelKey(TimelineViewModel::class)
    abstract fun bindTimelineViewModel(viewModel: TimelineViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeFriendsFragment(): FriendsFragment

    @Binds
    @IntoMap
    @ViewModelKey(FriendsViewModel::class)
    abstract fun bindSignUpViewModel(viewModel: FriendsViewModel): ViewModel
}
