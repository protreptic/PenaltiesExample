package test.p00.dependecies.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import test.p00.presentation.activity.MainActivity

@Module(includes = [  ])
abstract class ActivityModule {

    @Suppress("unused")
    @ContributesAndroidInjector
    abstract fun contributeMainActivityInjector(): MainActivity

}