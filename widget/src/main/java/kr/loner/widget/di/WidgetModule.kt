package kr.loner.widget.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.loner.core.domain.usecase.GetBookmarkedSessionListUseCase
import kr.loner.core.domain.usecase.GetSessionUseCase

@EntryPoint
@InstallIn(SingletonComponent::class)
interface WidgetModule {

    fun getBookmarkedSessionsUseCase(): GetBookmarkedSessionListUseCase

    fun getSessionUseCase(): GetSessionUseCase
}