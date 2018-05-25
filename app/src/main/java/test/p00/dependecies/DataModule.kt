package test.p00.dependecies

import android.content.Context
import dagger.Module
import dagger.Provides
import test.p00.data.repository.onboarding.OnBoardingRepository
import test.p00.data.repository.onboarding.datasource.OnBoardingDataSource
import test.p00.data.repository.onboarding.datasource.impl.RealmOnBoardingDataSource
import test.p00.data.repository.onboarding.impl.OnBoardingRepositoryImpl
import test.p00.data.repository.settings.SettingsRepository
import test.p00.data.repository.settings.datasource.SettingsDataSource
import test.p00.data.repository.settings.datasource.impl.SharedPreferencesSettingsDataSource
import test.p00.data.repository.settings.impl.SettingsRepositoryImpl
import test.p00.data.repository.user.datasource.UserDataSource
import test.p00.data.repository.user.datasource.impl.RealmUserDataSource
import test.p00.data.repository.user.impl.UserRepositoryImpl
import test.p00.domain.onboarding.OnBoardingInteractor

@Module
class DataModule {

    @Provides
    fun provideOnBoardingInteractor(
            onBoardingRepository: OnBoardingRepository,
            settingsRepository: SettingsRepository) =
                    OnBoardingInteractor(onBoardingRepository, settingsRepository)

    @Provides
    fun provideUserRepository(userDataSource: UserDataSource) =
            UserRepositoryImpl(userDataSource)

    @Provides
    fun provideUserDataSource() =
            RealmUserDataSource()

    @Provides
    fun provideOnBoardingRepository(settingsDataSource: OnBoardingDataSource) =
            OnBoardingRepositoryImpl(settingsDataSource)

    @Provides
    fun provideOnBoardingDataSource() =
            RealmOnBoardingDataSource()

    @Provides
    fun provideSettingsRepository(settingsDataSource: SettingsDataSource) =
            SettingsRepositoryImpl(settingsDataSource)

    @Provides
    fun provideSettingsDataSource(context: Context) =
            SharedPreferencesSettingsDataSource(context)

}