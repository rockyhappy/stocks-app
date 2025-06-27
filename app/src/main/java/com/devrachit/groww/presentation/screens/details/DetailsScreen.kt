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
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.devrachit.groww.R
import com.devrachit.groww.domain.models.StockType
import com.devrachit.groww.presentation.screens.details.components.ExpandableContainer
import com.devrachit.groww.presentation.screens.details.components.PriceRangeIndicator
import com.devrachit.groww.presentation.screens.home.components.Logo
import com.devrachit.groww.presentation.screens.home.components.StockItem
import com.devrachit.groww.ui.theme.TextStyleInter10Lh12Fw400
import com.devrachit.groww.ui.theme.TextStyleInter12Lh16Fw400
import com.devrachit.groww.ui.theme.TextStyleInter12Lh16Fw500
import com.devrachit.groww.ui.theme.TextStyleInter12Lh16Fw700
import com.devrachit.groww.ui.theme.TextStyleInter14Lh20Fw600
import com.devrachit.groww.ui.theme.TextStyleInter14Lh24Fw700
import com.devrachit.groww.ui.theme.TextStyleInter16Lh24Fw700
import com.devrachit.groww.ui.theme.TextStyleInter22Lh36Fw700
import com.devrachit.groww.utility.composeUtility.sdp
import com.devrachit.groww.utility.constants.Constants.Companion.TOP_GAINERS
import com.devrachit.groww.utility.constants.Constants.Companion.TOP_LOSERS

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun DetailsScreen(
    title: String,
    onRefresh: () -> Unit,
    uiState: DetailsScreenUiState
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
                    Row()
                    {
                        Text(
                            text = title,
                            modifier = Modifier.padding(top = 16.sdp, start = 16.sdp),
                            color = colorResource(R.color.black),
                            style = TextStyleInter22Lh36Fw700()
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
            if (!uiState.isLoading && uiState.companyDetails != null)
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(start = 16.sdp, end = 16.sdp)
                ) {
                    Row(
                        modifier = Modifier

                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Logo(
                            name = uiState.ticker,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(46.sdp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 16.sdp),
                            verticalArrangement = Arrangement.Center
                        )
                        {
                            Text(
                                text = uiState.companyDetails.name,
                                modifier = Modifier.padding(),
                                color = colorResource(R.color.black),
                                style = TextStyleInter12Lh16Fw700(),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                            Text(
                                text = "${uiState.companyDetails.symbol}, ${uiState.companyDetails.assetType}",
                                modifier = Modifier.padding(),
                                color = colorResource(R.color.black),
                                style = TextStyleInter12Lh16Fw400(),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                            Text(
                                text = uiState.companyDetails.exchange,
                                modifier = Modifier.padding(),
                                color = colorResource(R.color.black),
                                style = TextStyleInter12Lh16Fw500(),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )
                        }
                    }
                    Text(
                        text = "Analyst Target Price: $${uiState.companyDetails.analystTargetPrice}",
                        modifier = Modifier.padding(start = 16.sdp, top = 16.sdp, end = 16.sdp),
                        color = colorResource(R.color.black),
                        style = TextStyleInter16Lh24Fw700(),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Text(
                        text = "Dividend Yield: ${uiState.companyDetails.dividendYield}",
                        modifier = Modifier.padding(horizontal = 16.sdp, vertical = 0.sdp),
                        color = colorResource(R.color.blue_notification),
                        style = TextStyleInter12Lh16Fw400(),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Box(
                        modifier = Modifier
                            .padding(vertical = 16.sdp)
                            .fillMaxWidth()
                            .height(400.sdp)
                            .border(
                                border = BorderStroke(
                                    width = 1.sdp,
                                    color = colorResource(R.color.black).copy(alpha = 0.2f)
                                ), shape = RoundedCornerShape(16.sdp)
                            )
                    )
                    ExpandableContainer(
                        heading = "About ${uiState.companyDetails.symbol}",
                        description = uiState.companyDetails.description
                    )
                    Text(
                        text = "Sectors and Industry: " + uiState.companyDetails.symbol,
                        modifier = Modifier.padding(top = 16.sdp),
                        color = colorResource(R.color.black),
                        style = TextStyleInter12Lh16Fw700(),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Column()
                    {
                        Text(
                            text = "Industry: " + uiState.companyDetails.industry,
                            modifier = Modifier
                                .padding(start = 10.sdp, top = 5.sdp)
                                .widthIn(max = 400.sdp)
                                .border(
                                    border = BorderStroke(
                                        width = 2.sdp,
                                        color = colorResource(R.color.black).copy(alpha = 0.2f)
                                    ), shape = RoundedCornerShape(16.sdp)
                                )
                                .padding(horizontal = 10.sdp, vertical = 5.sdp),
                            color = colorResource(R.color.black),
                            style = TextStyleInter10Lh12Fw400(),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1

                        )
                        Text(
                            text = "Sector: " + uiState.companyDetails.sector,
                            modifier = Modifier
                                .widthIn(max = 200.sdp)
                                .padding(10.sdp)
                                .border(
                                    border = BorderStroke(
                                        width = 2.sdp,
                                        color = colorResource(R.color.black).copy(alpha = 0.2f)
                                    ), shape = RoundedCornerShape(16.sdp)
                                )
                                .padding(horizontal = 10.sdp, vertical = 5.sdp),
                            color = colorResource(R.color.black),
                            style = TextStyleInter10Lh12Fw400(),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1

                        )
                    }
                    Divider(
                        color = colorResource(R.color.black).copy(alpha = 0.1f),
                        modifier = Modifier.padding(vertical = 10.sdp)
                    )
                    val weekLow = uiState.companyDetails.weekLow52.toFloatOrNull() ?: 0f
                    val weekHigh = uiState.companyDetails.weekHigh52.toFloatOrNull() ?: 1f
                    val currentPrice = uiState.companyDetails.analystTargetPrice.toFloatOrNull() ?: 0f
                    val progress = ((currentPrice - weekLow) / (weekHigh - weekLow)).coerceIn(0f, 1f)
                    val dynamicPaddingStart = with(LocalDensity.current) { (progress * (LocalConfiguration.current.screenWidthDp - 120)).dp }

                    Row(
                        modifier = Modifier
                            .padding(10.sdp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Text(
                            text = "52 Week\nLow\n$" + uiState.companyDetails.weekLow52,
                            modifier = Modifier.padding(),
                            color = colorResource(R.color.black),
                            style = TextStyleInter12Lh16Fw400(),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 3,
                            textAlign = TextAlign.Center
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .height(60.sdp)

                        ){
                            Text(
                                text = "Current\n$" + uiState.companyDetails.analystTargetPrice +"\n↓",
                                modifier = Modifier.padding(start = dynamicPaddingStart),
                                color = colorResource(R.color.black),
                                style = TextStyleInter12Lh16Fw400(),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 3,
                                textAlign = TextAlign.Center
                            )
                            Box(
                                modifier = Modifier
                                    .padding(start=10.sdp, end=10.sdp)
                                    .fillMaxWidth()
                                    .height(10.sdp)
                                    .clip(RoundedCornerShape(20.sdp))
                                    .background(color = colorResource(R.color.easy_filled_blue))

                            )
                        }
                        Text(
                            text = "52 Week\nHigh\n$" + uiState.companyDetails.weekHigh52,
                            modifier = Modifier.padding(),
                            color = colorResource(R.color.black),
                            style = TextStyleInter12Lh16Fw400(),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 3,
                            textAlign = TextAlign.Center
                        )
                    }
                    Divider(
                        color = colorResource(R.color.black).copy(alpha = 0.1f),
                        modifier = Modifier.padding(vertical = 10.sdp)
                    )
//                    PriceRangeIndicator(
//                        week52Low = 10f,
//                        currentPrice = 20f,
//                        week52High = 50f
//                    )
//                Text(
//                    text = uiState.toString(),
//                    color = colorResource(R.color.black),
//                )
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