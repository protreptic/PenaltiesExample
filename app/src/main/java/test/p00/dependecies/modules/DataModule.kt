package test.p00.dependecies.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import test.p00.data.repository.conversation.ConversationRepository
import test.p00.data.repository.conversation.datasource.ConversationDataSource
import test.p00.data.repository.conversation.datasource.impl.CloudConversationDataSource
import test.p00.data.repository.conversation.datasource.impl.MockConversationDataSource
import test.p00.data.repository.conversation.impl.ConversationRepositoryImpl
import test.p00.data.repository.conversations.ConversationsRepository
import test.p00.data.repository.conversations.datasource.ConversationsDataSource
import test.p00.data.repository.conversations.datasource.impl.CloudConversationsDataSource
import test.p00.data.repository.conversations.datasource.impl.MockConversationsDataSource
import test.p00.data.repository.conversations.impl.ConversationsRepositoryImpl
import test.p00.data.repository.countries.CountriesRepository
import test.p00.data.repository.countries.datasource.CountriesDataSource
import test.p00.data.repository.countries.datasource.impl.CountriesDataSourceImpl
import test.p00.data.repository.countries.impl.CountriesRepositoryImpl
import test.p00.data.repository.onboarding.OnBoardingRepository
import test.p00.data.repository.onboarding.datasource.OnBoardingDataSource
import test.p00.data.repository.onboarding.datasource.impl.RealmOnBoardingDataSource
import test.p00.data.repository.onboarding.impl.OnBoardingRepositoryImpl
import test.p00.data.repository.settings.SettingsRepository
import test.p00.data.repository.settings.datasource.SettingsDataSource
import test.p00.data.repository.settings.datasource.impl.SharedPreferencesSettingsDataSource
import test.p00.data.repository.settings.impl.SettingsRepositoryImpl
import test.p00.data.repository.user.UserRepository
import test.p00.data.repository.user.datasource.UserDataSource
import test.p00.data.repository.user.datasource.impl.RealmUserDataSource
import test.p00.data.repository.user.impl.UserRepositoryImpl
import test.p00.data.storage.websocket.WebSocketConnection
import javax.inject.Named

@Module(includes = [ NetworkModule::class ])
class DataModule {

    @Provides
    fun provideConversationRepository(
            @Named("cache") cache: ConversationDataSource,
            @Named("cloud") cloud: ConversationDataSource): ConversationRepository =
                    ConversationRepositoryImpl(cache, cloud)

    @Provides
    @Named("cache")
    fun provideCacheConversationDataSource(): ConversationDataSource =
            MockConversationDataSource()

    @Provides
    @Named("cloud")
    fun provideCloudConversationDataSource(connection: WebSocketConnection): ConversationDataSource =
            CloudConversationDataSource("1", connection)

    @Provides
    fun provideConversationsRepository(
            @Named("cache") cache: ConversationsDataSource,
            @Named("cloud") cloud: ConversationsDataSource): ConversationsRepository =
                    ConversationsRepositoryImpl(cache, cloud)

    @Provides
    @Named("cache")
    fun provideCacheConversationsDataSource(): ConversationsDataSource =
            MockConversationsDataSource()

    @Provides
    @Named("cloud")
    fun provideCloudConversationsDataSource(): ConversationsDataSource =
            CloudConversationsDataSource()

    @Provides
    fun provideCountriesRepository(
            countriesDataSource: CountriesDataSource): CountriesRepository =
                    CountriesRepositoryImpl(countriesDataSource)

    @Provides
    fun provideCountriesDataSource(context: Context): CountriesDataSource =
            CountriesDataSourceImpl(context)

    @Provides
    fun provideUserRepository(userDataSource: UserDataSource): UserRepository =
            UserRepositoryImpl(userDataSource)

    @Provides
    fun provideUserDataSource(): UserDataSource =
            RealmUserDataSource()

    @Provides
    fun provideOnBoardingRepository(
            settingsDataSource: OnBoardingDataSource): OnBoardingRepository =
                    OnBoardingRepositoryImpl(settingsDataSource)

    @Provides
    fun provideOnBoardingDataSource(context: Context): OnBoardingDataSource =
            RealmOnBoardingDataSource(context)

    @Provides
    fun provideSettingsRepository(
            settingsDataSource: SettingsDataSource): SettingsRepository =
                    SettingsRepositoryImpl(settingsDataSource)

    @Provides
    fun provideSettingsDataSource(context: Context): SettingsDataSource =
            SharedPreferencesSettingsDataSource(context)

}