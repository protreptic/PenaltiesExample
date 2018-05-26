package test.p00.auxiliary.dependecies.modules

import dagger.Module
import dagger.Provides
import test.p00.data.repository.conversation.datasource.ConversationDataSource
import test.p00.data.repository.onboarding.OnBoardingRepository
import test.p00.data.repository.settings.SettingsRepository
import test.p00.domain.conversation.ConversationInteractor
import test.p00.domain.launcher.LauncherInteractor
import test.p00.domain.onboarding.OnBoardingInteractor
import javax.inject.Named

/**
 * Created by Peter Bukhal on 5/26/18.
 */
@Module(includes = [ DataModule::class ])
class DomainModule {

    @Provides
    fun provideLauncherInteractor(
            settingsRepository: SettingsRepository): LauncherInteractor =
                    LauncherInteractor(settingsRepository)

    @Provides
    fun provideConversationInteractor(
            @Named("cache") cache: ConversationDataSource,
            @Named("cloud") cloud: ConversationDataSource): ConversationInteractor =
                    ConversationInteractor(cache, cloud)

    @Provides
    fun provideOnBoardingInteractor(
            onBoardingRepository: OnBoardingRepository,
            settingsRepository: SettingsRepository): OnBoardingInteractor =
                    OnBoardingInteractor(onBoardingRepository, settingsRepository)

}