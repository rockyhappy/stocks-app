package com.devrachit.groww.presentation.screens.watchlist


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrachit.groww.data.local.entity.WatchlistEntity
import com.devrachit.groww.domain.usecases.watchlistDetails.AddWatchlist
import com.devrachit.groww.domain.usecases.watchlistDetails.DeleteWatchlist
import com.devrachit.groww.domain.usecases.watchlistDetails.GetAllWatchlist
import com.devrachit.groww.utility.networkUtility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchlistScreenViewmodel @Inject constructor(
    private val getWatchlist: GetAllWatchlist,
    private val addWatchlist: AddWatchlist,
    private val deleteWatchlist: DeleteWatchlist
) : ViewModel() {
    private val _uiState = MutableStateFlow(WatchlistScreenUiStates())
    val uiState: StateFlow<WatchlistScreenUiStates> = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllWatchlist()
        }
    }
    fun onRefresh(){
        viewModelScope.launch(Dispatchers.IO) {
            getAllWatchlist()
        }
    }
    fun onWatchlistEntryChanged(watchlistName: String) {
        _uiState.update {
            it.copy(watchlistEntry =  watchlistName)
        }
    }

    private suspend fun getAllWatchlist() {
        getWatchlist.invoke().collectLatest { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(isLoading = false, allWatchlist = result.data ?: emptyList())
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                }

                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun addWatchlist(watchlistName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = WatchlistEntity(
                name = watchlistName,
                count = 0
            )
            println(data)

            addWatchlist.invoke(data).collectLatest { result ->
                println(result)
                when (result) {
                    is Resource.Success -> {
                        getAllWatchlist()
                    }

                    is Resource.Error -> {

                    }

                    is Resource.Loading -> {

                    }
                }
            }
        }
    }
    fun deleteWatchlist(watchlistEntity: WatchlistEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteWatchlist.invoke(watchlistEntity).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        getAllWatchlist()
                    }
                    is Resource.Error -> {

                    }
                    is Resource.Loading -> {

                    }
                }
            }
        }
    }
}