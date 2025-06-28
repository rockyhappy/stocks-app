package com.devrachit.groww.presentation.screens.home

import android.widget.GridView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.devrachit.groww.R
import com.devrachit.groww.domain.models.Stock
import com.devrachit.groww.domain.models.StockType
import com.devrachit.groww.presentation.screens.home.components.HomeScreenShimmer
import com.devrachit.groww.presentation.screens.home.components.SeeMoreCard
import com.devrachit.groww.presentation.screens.home.components.StockItem
import com.devrachit.groww.presentation.screens.home.components.StockListSection
import com.devrachit.groww.ui.theme.TextStyleInter18Lh24Fw700
import com.devrachit.groww.ui.theme.TextStyleInter20Lh24Fw500
import com.devrachit.groww.ui.theme.TextStyleInter20Lh24Fw600
import com.devrachit.groww.utility.composeUtility.sdp
import com.devrachit.groww.utility.constants.Constants.Companion.API_KEY_FAILURE
import com.devrachit.groww.utility.constants.Constants.Companion.MOSTLY_TRADED
import com.devrachit.groww.utility.constants.Constants.Companion.TOP_GAINERS
import com.devrachit.groww.utility.constants.Constants.Companion.TOP_LOSERS

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    uiState: HomeScreenUiState,
    title: String,
    onNavigateToDetail: (stock: Stock) -> Unit,
    onNavigationToDisplay: (type: StockType) -> Unit,
    onRefresh: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    )
    {
        val pullRefreshState = rememberPullRefreshState(
            refreshing = uiState.isLoading,
            onRefresh = {onRefresh()}
        )

       Column(
           modifier = Modifier
               .pullRefresh(pullRefreshState)
               .fillMaxSize()
               .verticalScroll(rememberScrollState())
               .background(color = colorResource(R.color.white))

       ){
           if(!uiState.isLoading && uiState.gainersList== emptyList<Stock>())
           {
               Text(
                   text = API_KEY_FAILURE,
                   style = TextStyleInter20Lh24Fw600(),
                   color = colorResource(R.color.black),
                   modifier = Modifier.padding(16.sdp)
               )
           }


           StockListSection(
               title = TOP_GAINERS,
               stockList = uiState.gainersList,
               isLoading = uiState.isLoading,
               onNavigationToDisplay = {onNavigationToDisplay(StockType.Gainer())},
               stockType = StockType.Gainer(),
               onStockClick = {onNavigateToDetail(it)}
           )

           StockListSection(
               title = TOP_LOSERS,
               stockList = uiState.losersList,
               isLoading = uiState.isLoading,
               onNavigationToDisplay = {onNavigationToDisplay(StockType.Loser())},
               stockType = StockType.Loser(),
               onStockClick = {onNavigateToDetail(it)}
           )

           StockListSection(
               title = MOSTLY_TRADED,
               stockList = uiState.activeList,
               isLoading = uiState.isLoading,
               onNavigationToDisplay = {onNavigationToDisplay(StockType.Active())},
               stockType = StockType.Active(),
               onStockClick = {onNavigateToDetail(it)}
           )
           Spacer(modifier = Modifier.height(16.sdp))

       }
        PullRefreshIndicator(
            refreshing = uiState.isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            backgroundColor = colorResource(id = R.color.white),
            contentColor = colorResource(id = R.color.black)
        )
    }
}
