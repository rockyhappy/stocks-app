package com.devrachit.groww.di.modules

import com.devrachit.groww.data.repository.remote.HomeRepositoryImpl
import com.devrachit.groww.domain.repository.remote.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsHomeRepository(repository: HomeRepositoryImpl): HomeRepository

}