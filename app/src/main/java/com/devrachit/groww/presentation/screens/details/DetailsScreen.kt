package com.devrachit.groww.presentation.screens.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.devrachit.groww.domain.models.StockType
import com.devrachit.groww.presentation.screens.details.components.CompanyInfoHeader
import com.devrachit.groww.presentation.screens.details.components.ExpandableContainer
import com.devrachit.groww.presentation.screens.details.components.FinancialMetricsSection
import com.devrachit.groww.presentation.screens.details.components.PriceInfoSection
import com.devrachit.groww.presentation.screens.details.components.SectorIndustrySection
import com.devrachit.groww.presentation.screens.details.components.StockChart
import com.devrachit.groww.presentation.screens.details.components.WeekRangeIndicator
import com.devrachit.groww.ui.theme.TextStyleInter22Lh36Fw700
import com.devrachit.groww.utility.composeUtility.sdp
import com.devrachit.groww.utility.constants.Constants.Companion.TOP_GAINERS
import com.devrachit.groww.utility.constants.Constants.Companion.TOP_LOSERS
import com.devrachit.groww.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun DetailsScreen(
    title: String,
    onRefresh: () -> Unit,
    uiState: DetailsScreenUiState,
    graphState: GraphState,
    onGraphTypeChange: (GraphType) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isLoading,
        onRefresh = onRefresh
    )

    LaunchedEffect(Unit) {
        onRefresh()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = title,
                            modifier = Modifier.padding(top = 16.sdp, start = 16.sdp),
                            color = colorResource(R.color.black),
                            style = TextStyleInter22Lh36Fw700()
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            painter = if (uiState.isBookmarkAdded) painterResource(R.drawable.ic_bookmark_filled) else painterResource(
                                R.drawable.ic_bookmark_outlined
                            ),
                            contentDescription = "Error",
                            tint = colorResource(R.color.black),
                            modifier = Modifier
                                .padding(end = 16.sdp)
                                .size(24.sdp)
                                .clickable {}
                        )
                    }
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
            if (!uiState.isLoading && uiState.companyDetails != null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(start = 16.sdp, end = 16.sdp)
                ) {
                    CompanyInfoHeader(
                        ticker = uiState.ticker,
                        companyName = uiState.companyDetails.name,
                        symbol = uiState.companyDetails.symbol,
                        assetType = uiState.companyDetails.assetType,
                        exchange = uiState.companyDetails.exchange
                    )

                    PriceInfoSection(
                        analystTargetPrice = uiState.companyDetails.analystTargetPrice,
                        dividendYield = uiState.companyDetails.dividendYield
                    )

                    StockChart(
                        graphState = graphState,
                        isLoading = graphState.isLoading,
                        onGraphTypeChange = onGraphTypeChange
                    )

                    ExpandableContainer(
                        heading = "About ${uiState.companyDetails.symbol}",
                        description = uiState.companyDetails.description
                    )

                    SectorIndustrySection(
                        symbol = uiState.companyDetails.symbol,
                        industry = uiState.companyDetails.industry,
                        sector = uiState.companyDetails.sector
                    )

                    Divider(
                        color = colorResource(R.color.black).copy(alpha = 0.1f),
                        modifier = Modifier.padding(vertical = 10.sdp)
                    )

                    WeekRangeIndicator(
                        weekLow52 = uiState.companyDetails.weekLow52,
                        weekHigh52 = uiState.companyDetails.weekHigh52,
                        currentPrice = uiState.companyDetails.analystTargetPrice
                    )

                    Divider(
                        color = colorResource(R.color.black).copy(alpha = 0.1f),
                        modifier = Modifier.padding(vertical = 10.sdp)
                    )

                    FinancialMetricsSection(
                        marketCap = uiState.companyDetails.marketCapitalization,
                        peRatio = uiState.companyDetails.peRatio,
                        pegRatio = uiState.companyDetails.pegRatio,
                        beta = uiState.companyDetails.beta,
                        bookValue = uiState.companyDetails.bookValue,
                        dividendPerShare = uiState.companyDetails.dividendPerShare,
                        returnOnAssets = uiState.companyDetails.returnOnAssetsTTM,
                        returnOnEquity = uiState.companyDetails.returnOnEquityTTM
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
