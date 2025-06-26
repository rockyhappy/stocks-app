package com.devrachit.groww.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Singleton


// Define DataStore at the package level to ensure there's only one instance
private val Context.dataStore by preferencesDataStore(name = "user_preferences")

@Singleton
class DataStoreRepository(private val context: Context) {

    // This companion object provides a thread-safe way of creating and accessing
    // a singleton instance of the DataStoreRepository. The INSTANCE variable is marked
    // with '@Volatile' to ensure changes made by one thread are immediately visible to
    // others. The 'getInstance' function uses double-checked locking with synchronization
    // to prevent multiple instances from being created in multithreaded environments,
    // thus ensuring only a single instance of DataStoreRepository exists throughout
    // the application's lifecycle.
    companion object {

        @Volatile
        private var INSTANCE: DataStoreRepository? = null

        fun getInstance(context: Context): DataStoreRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = DataStoreRepository(context.applicationContext)
                INSTANCE = instance
                instance
            }
        }
    }

}