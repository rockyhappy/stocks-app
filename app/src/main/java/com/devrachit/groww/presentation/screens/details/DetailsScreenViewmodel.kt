package com.devrachit.groww.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrachit.groww.data.local.entity.StocksEntity
import com.devrachit.groww.data.local.entity.WatchlistEntity
import com.devrachit.groww.domain.models.Stock
import com.devrachit.groww.domain.usecases.CompanyStocksDetails.GetCompanyDetails
import com.devrachit.groww.domain.usecases.CompanyStocksDetails.GetDailyGraphData
import com.devrachit.groww.domain.usecases.CompanyStocksDetails.GetIntraDayGraphData
import com.devrachit.groww.domain.usecases.CompanyStocksDetails.GetMonthlyGraphData
import com.devrachit.groww.domain.usecases.CompanyStocksDetails.GetWeeklyGraphData
import com.devrachit.groww.domain.usecases.watchlistDetails.AddStockToWatchlist
import com.devrachit.groww.domain.usecases.watchlistDetails.AddWatchlist
import com.devrachit.groww.domain.usecases.watchlistDetails.DeleteStockFromWatchlist
import com.devrachit.groww.domain.usecases.watchlistDetails.DeleteWatchlist
import com.devrachit.groww.domain.usecases.watchlistDetails.GetAllWatchlist
import com.devrachit.groww.domain.usecases.watchlistDetails.isStockInWatchList
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
class DetailsScreenViewmodel @Inject constructor(
    private val GetCompanyDetailsUsecase: GetCompanyDetails,
    private val getIntraDayOhlcvData: GetIntraDayGraphData,
    private val getDailyGraphData: GetDailyGraphData,
    private val getMonthlyGraphData: GetMonthlyGraphData,
    private val getWeeklyGraphData: GetWeeklyGraphData,
    private val getWatchlist: GetAllWatchlist,
    private val addWatchlist: AddWatchlist,
    private val deleteWatchlist: DeleteWatchlist,
    private val isStockInWatchlist: isStockInWatchList,
    private val AddStockToWatchlist: AddStockToWatchlist,
    private val deleteStockFromWatchlist: DeleteStockFromWatchlist
) : ViewModel() {


    private val _uiState = MutableStateFlow(DetailsScreenUiState())
    val uiState: StateFlow<DetailsScreenUiState> = _uiState.asStateFlow()

    private val _graphState = MutableStateFlow(GraphState())
    val graphState: StateFlow<GraphState> = _graphState.asStateFlow()

    init {
//        getCompanyDetails()
    }

    fun setTicker(ticker: String) {
        _uiState.update { it.copy(ticker = ticker) }

    }

    fun onWatchlistEntryChanged(watchlistName: String) {
        _uiState.update {
            it.copy(
                watchlistEntry = watchlistName
            )
        }
    }

    fun setStock(stock: Stock) {
        _uiState.update { it.copy(stock = stock) }
        isStockInWatchlist(ticker = _uiState.value.stock?.ticker ?: _uiState.value.ticker)
        viewModelScope.launch(Dispatchers.IO) {
            getAllWatchlist()
        }

    }

    private fun isStockInWatchlist(ticker: String) {
        viewModelScope.launch(Dispatchers.IO) {
            isStockInWatchlist.invoke(ticker).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {

                    }

                    is Resource.Success -> {
                        if (result.data != null && result.data.isNotEmpty()) {
                            _uiState.update {
                                it.copy(
                                    isBookmarkAdded = true,
                                    stockWatchlist = result.data
                                )
                            }

                        } else {
                            _uiState.update {
                                it.copy(
                                    isBookmarkAdded = false,
                                    stockWatchlist = emptyList()
                                )
                            }
                        }
                    }

                    is Resource.Error -> {

                    }
                }
            }
        }
    }

    fun getCompanyDetails() {
        val coroutineScope = viewModelScope
        coroutineScope.launch(Dispatchers.IO) {
            getCompanyInfo(ticker = _uiState.value.ticker)
        }
        coroutineScope.launch(Dispatchers.IO) {
            when (_graphState.value.graphType) {
                GraphType.INTRA_DAY -> getIntraDayGraphData(ticker = _uiState.value.ticker)
                GraphType.DAILY -> getDailyOhlcvData(ticker = _uiState.value.ticker)
                GraphType.WEEKLY -> getWeeklyOhlcvData(ticker = _uiState.value.ticker)
                GraphType.MONTHLY -> getMonthlyOhlcvData(ticker = _uiState.value.ticker)
            }
        }
    }

    fun setGraphType(graphType: GraphType) {
        _graphState.update { it.copy(graphType = graphType) }
        getGraphData()
    }

    fun getGraphData() {
        viewModelScope.launch(Dispatchers.IO) {
            when (_graphState.value.graphType) {
                GraphType.INTRA_DAY -> getIntraDayGraphData(ticker = _uiState.value.ticker)
                GraphType.DAILY -> getDailyOhlcvData(ticker = _uiState.value.ticker)
                GraphType.WEEKLY -> getWeeklyOhlcvData(ticker = _uiState.value.ticker)
                GraphType.MONTHLY -> getMonthlyOhlcvData(ticker = _uiState.value.ticker)
            }
        }
    }

    private suspend fun getCompanyInfo(ticker: String) {
        GetCompanyDetailsUsecase.invoke(ticker).collectLatest { response ->
            when (response) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = response.message
                        )
                    }
                }

                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            companyDetails = response.data,
                            error = null
                        )
                    }
                }
            }
        }
    }

    private suspend fun getIntraDayGraphData(ticker: String) {
        getIntraDayOhlcvData.invoke(ticker).collectLatest { response ->
            when (response) {
                is Resource.Loading -> {
                    _graphState.update { it.copy(isLoading = true) }
                }

                is Resource.Error -> {
                    _graphState.update {
                        it.copy(
                            isLoading = false,
                            error = response.message
                        )
                    }
                }

                is Resource.Success -> {
                    _graphState.update {
                        it.copy(
                            isLoading = false,
                            error = null
                        )
                    }
                    _graphState.update {
                        it.copy(
                            data = response.data ?: emptyMap(),
                        )
                    }

                }
            }
        }
    }

    private suspend fun getDailyOhlcvData(ticker: String) {
        getDailyGraphData.invoke(ticker).collectLatest { response ->
            when (response) {
                is Resource.Loading -> {
                    _graphState.update { it.copy(isLoading = true) }
                }

                is Resource.Error -> {
                    _graphState.update {
                        it.copy(
                            isLoading = false,
                            error = response.message
                        )
                    }
                }

                is Resource.Success -> {
                    _graphState.update {
                        it.copy(
                            isLoading = false,
                            error = null
                        )
                    }
                    _graphState.update {
                        it.copy(
                            data = response.data ?: emptyMap(),
                        )
                    }

                }
            }
        }
    }

    private suspend fun getWeeklyOhlcvData(ticker: String) {
        getWeeklyGraphData.invoke(ticker).collectLatest { response ->
            when (response) {
                is Resource.Loading -> {
                    _graphState.update { it.copy(isLoading = true) }
                }

                is Resource.Error -> {
                    _graphState.update {
                        it.copy(
                            isLoading = false,
                            error = response.message
                        )
                    }
                }

                is Resource.Success -> {
                    _graphState.update {
                        it.copy(
                            isLoading = false,
                            error = null
                        )
                    }
                    _graphState.update {
                        it.copy(
                            data = response.data ?: emptyMap(),
                        )
                    }

                }
            }
        }
    }

    private suspend fun getMonthlyOhlcvData(ticker: String) {
        getMonthlyGraphData.invoke(ticker).collectLatest { response ->
            when (response) {
                is Resource.Loading -> {
                    _graphState.update { it.copy(isLoading = true) }
                }

                is Resource.Error -> {
                    _graphState.update {
                        it.copy(
                            isLoading = false,
                            error = response.message ?: "An unexpected error occurred"
                        )
                    }
                }

                is Resource.Success -> {
                    _graphState.update {
                        it.copy(
                            isLoading = false,
                            error = null
                        )
                    }
                    _graphState.update {
                        it.copy(
                            data = response.data ?: emptyMap(),
                        )
                    }

                }
            }
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
                        isStockInWatchlist(ticker = _uiState.value.stock?.ticker ?: _uiState.value.ticker)
                    }

                    is Resource.Error -> {

                    }

                    is Resource.Loading -> {

                    }
                }
            }
        }
    }

    fun addStockToWatchlist(watchlistEntity: WatchlistEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            AddStockToWatchlist.invoke(
                stock = StocksEntity(
                    ticker = _uiState.value.stock?.ticker ?: "",
                    changeAmount = _uiState.value.stock?.changeAmount ?: "0.0",
                    changePercentage = _uiState.value.stock?.changePercentage ?: "0.0",
                    price = _uiState.value.stock?.price ?: "0.0",
                    volume = _uiState.value.stock?.volume ?: "0.0"
                ),
                watchlistEntity = watchlistEntity
            ).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        getAllWatchlist()
                        isStockInWatchlist(ticker = _uiState.value.stock?.ticker ?: _uiState.value.ticker)
                    }
                    is Resource.Error -> {

                    }
                    is Resource.Loading -> {

                    }
                }
            }
        }
    }

    fun deleteFromWatchlist(watchlistEntity: WatchlistEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteStockFromWatchlist.invoke(
                stock = StocksEntity(
                    ticker = _uiState.value.stock?.ticker ?: "",
                    changeAmount = _uiState.value.stock?.changeAmount ?: "0.0",
                    changePercentage = _uiState.value.stock?.changePercentage ?: "0.0",
                    price = _uiState.value.stock?.price ?: "0.0",
                    volume = _uiState.value.stock?.volume ?: "0.0"
                ),
                watchlistEntity = watchlistEntity
            ).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        getAllWatchlist()
                        isStockInWatchlist(ticker = _uiState.value.stock?.ticker ?: _uiState.value.ticker)
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
