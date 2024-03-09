package com.elanyudho.core.di

import com.elanyudho.core.domain.repository.ToolRepository
import com.elanyudho.core.domain.usecase.BorrowToolUseCase
import com.elanyudho.core.domain.usecase.GetAllFriendUseCase
import com.elanyudho.core.domain.usecase.GetAllToolUseCase
import com.elanyudho.core.domain.usecase.GetDetailFriendUseCase
import com.elanyudho.core.domain.usecase.ReturnToolUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object UseCaseModule {
    @Provides
    @ActivityScoped
    fun provideGetAllFriendUseCase(repository: ToolRepository) = GetAllFriendUseCase(repository)

    @Provides
    @ActivityScoped
    fun provideGetAllToolUseCase(repository: ToolRepository) = GetAllToolUseCase(repository)

    @Provides
    @ActivityScoped
    fun provideBorrowToolUseCase(repository: ToolRepository) = BorrowToolUseCase(repository)

    @Provides
    @ActivityScoped
    fun provideReturnToolUseCase(repository: ToolRepository) = ReturnToolUseCase(repository)

    @Provides
    @ActivityScoped
    fun provideGetDetailFriendUseCase(repository: ToolRepository) = GetDetailFriendUseCase(repository)
}

