package com.devrachit.groww.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.devrachit.groww.data.local.entity.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

@Dao
interface SearchHistoryDao {

    @Query("SELECT * FROM search_history ORDER BY timestamp ASC")
    fun getAllSearches(): Flow<List<SearchHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(search: SearchHistoryEntity)

    @Query("DELETE FROM search_history WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM search_history WHERE `query` = :query LIMIT 1")
    suspend fun getSearchByQuery(query: String): SearchHistoryEntity?

    @Transaction
    suspend fun insertWithLimit(search: SearchHistoryEntity) {
        val existingSearch = getSearchByQuery(search.query)
        if (existingSearch != null) {
            deleteById(existingSearch.id)
        }

        val searches = getAllSearches().first()
        if (searches.size >= 5) {
            deleteById(searches[0].id)
        }
        insert(search)
    }
}