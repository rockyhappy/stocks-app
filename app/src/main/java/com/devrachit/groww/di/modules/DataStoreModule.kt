package com.devrachit.groww.di.modules

import android.content.Context
import com.devrachit.groww.data.local.database.AppDatabase
import com.devrachit.groww.data.local.datastore.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideUserPreferencesRepository(
        @ApplicationContext context: Context
    ): DataStoreRepository {
        return DataStoreRepository(context)
    }
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun providesWatchlistDao(database: AppDatabase) = database.watchlistDao()

    @Provides
    @Singleton
    fun providesSearchHistoryDao(database: AppDatabase) = database.searchHistoryDao()

}