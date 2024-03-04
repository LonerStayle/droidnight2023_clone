package kr.loner.core.data.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.loner.core.data.api.GithubRawApi
import kr.loner.core.data.api.fake.AssetsGithubRawApi
import kr.loner.core.data.repository.ContributorRepository
import kr.loner.core.data.repository.SessionRepository
import kr.loner.core.data.repository.SettingsRepository
import kr.loner.core.data.repository.SponsorRepository
import kr.loner.core.data.repository.impl.DefaultContributorRepository
import kr.loner.core.data.repository.impl.DefaultSessionRepository
import kr.loner.core.data.repository.impl.DefaultSettingsRepository
import kr.loner.core.data.repository.impl.DefaultSponsorRepository
import kr.loner.core.datastore.datasource.DefaultSessionPreferencesDataSource
import kr.loner.core.datastore.datasource.SessionPreferencesDataSource
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {
    @Binds
    abstract fun bindContributorRepository(
        repository: DefaultContributorRepository
    ): ContributorRepository

    @Binds
    abstract fun bindSettingsRepository(
        repository: DefaultSettingsRepository
    ): SettingsRepository

    @Binds
    abstract fun bindSessionLocalDataSource(
        dataSource: DefaultSessionPreferencesDataSource
    ): SessionPreferencesDataSource

    @InstallIn(SingletonComponent::class)
    @Module
    internal object FakeModule {
        @Provides
        @Singleton
        fun provideSponsorRepository(
            githubRawApi: GithubRawApi
        ): SponsorRepository = DefaultSponsorRepository(githubRawApi)

        @Provides
        @Singleton
        fun provideSessionRepository(
            githubRawApi: GithubRawApi,
            sessionDataSource: SessionPreferencesDataSource
        ): SessionRepository = DefaultSessionRepository(githubRawApi, sessionDataSource)

        @Provides
        @Singleton
        fun provideGithubRawApi(
            @ApplicationContext context: Context
        ): AssetsGithubRawApi = AssetsGithubRawApi(context)
    }
}