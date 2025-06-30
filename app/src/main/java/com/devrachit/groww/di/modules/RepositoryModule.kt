package com.devrachit.groww.di.modules

import android.content.Context
import com.devrachit.groww.data.local.database.AppDatabase
import com.devrachit.groww.data.repository.local.SearchLocalRepositoryImpl
import com.devrachit.groww.data.repository.local.WatchlistRepositoryImpl
import com.devrachit.groww.data.repository.remote.CompanyDetailsRepositoryImpl
import com.devrachit.groww.data.repository.remote.HomeRepositoryImpl
import com.devrachit.groww.data.repository.remote.SearchRemoteRepositoryImpl
import com.devrachit.groww.domain.repository.local.SearchLocalRepository
import com.devrachit.groww.domain.repository.local.WatchlistRepository
import com.devrachit.groww.domain.repository.remote.CompanyDetailsRepository
import com.devrachit.groww.domain.repository.remote.HomeRepository
import com.devrachit.groww.domain.repository.remote.SearchRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsHomeRepository(repository: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindsCompanyDetailsRepository(repository: CompanyDetailsRepositoryImpl): CompanyDetailsRepository

    @Binds
    abstract fun bindsWatchListRepository(repository: WatchlistRepositoryImpl): WatchlistRepository

    @Binds
    abstract fun bindsSearchRemoteRepository(repository: SearchRemoteRepositoryImpl): SearchRemoteRepository

    @Binds
    abstract fun bindsSearchLocalRepository(repository : SearchLocalRepositoryImpl): SearchLocalRepository

}