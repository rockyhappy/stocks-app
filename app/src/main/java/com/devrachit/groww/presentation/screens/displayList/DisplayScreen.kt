package com.devrachit.groww.presentation.screens.displayList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import com.devrachit.groww.R
import com.devrachit.groww.domain.models.StockType
import com.devrachit.groww.presentation.screens.home.components.StockItem
import com.devrachit.groww.ui.theme.TextStyleInter18Lh24Fw700
import com.devrachit.groww.ui.theme.TextStyleInter22Lh36Fw700
import com.devrachit.groww.utility.composeUtility.sdp
import com.devrachit.groww.utility.constants.Constants.Companion.MOSTLY_TRADED
import com.devrachit.groww.utility.constants.Constants.Companion.START_DESTINATION_INNER_NAV
import com.devrachit.groww.utility.constants.Constants.Companion.TOP_GAINERS
import com.devrachit.groww.utility.constants.Constants.Companion.TOP_LOSERS

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun DisplayListScreen(
    uiState: DisplayListUiState,
    onRefresh: () -> Unit,
    title: String,
    navigateToDetailsScreen: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isLoading,
        onRefresh = onRefresh
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        modifier = Modifier.padding(top = 16.sdp, start = 16.sdp),
                        color = colorResource(R.color.black),
                        style = TextStyleInter22Lh36Fw700()
                    )
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = colorResource(R.color.white)
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
                .padding(paddingValues)
                .background(color = colorResource(R.color.white))
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize() .nestedScroll(scrollBehavior.nestedScrollConnection),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(8.sdp)
            ) {
                items(uiState.data.size) { index ->
                    StockItem(
                        modifier = Modifier,
                        stock = uiState.data[index],
                        stockType = when (title) {
                            TOP_GAINERS -> StockType.Gainer()
                            TOP_LOSERS-> StockType.Loser()
                            else -> StockType.Active()

                        },
                        onCompanyClick = navigateToDetailsScreen
                    )
                }
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
}
