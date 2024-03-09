package com.elanyudho.core.di


import com.elanyudho.core.data.local.LocalDataSource
import com.elanyudho.core.data.local.room.ToolDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object  DataSourceModule {

    @Provides
    @ActivityScoped
    fun provideLocalDataSource(tool: ToolDao) = LocalDataSource(tool)
}