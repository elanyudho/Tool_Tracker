package com.elanyudho.core.di

import com.elanyudho.core.data.repository.ToolRepositoryImpl
import com.elanyudho.core.domain.repository.ToolRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {
    @Binds
    @ActivityScoped
    abstract fun bindToolRepository(repositoryImpl: ToolRepositoryImpl): ToolRepository

}