package test.p00.dependecies.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import test.p00.presentation.activity.MainActivity
import test.p00.presentation.countries.impl.CountriesFragment
import test.p00.presentation.launcher.impl.LauncherFragment
import test.p00.presentation.signup.impl.SignUpFragment
import test.p00.presentation.signup.verification.impl.SignUpVerificationFragment

@Suppress("unused")
@Module(includes = [  ])
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeSignUpFragmentInjector(): SignUpFragment

    @ContributesAndroidInjector
    abstract fun contributeCountriesFragmentInjector(): CountriesFragment

    @ContributesAndroidInjector
    abstract fun contributeSignUpVerificationFragmentInjector(): SignUpVerificationFragment

    @ContributesAndroidInjector
    abstract fun contributeLauncherFragmentInjector(): LauncherFragment

}