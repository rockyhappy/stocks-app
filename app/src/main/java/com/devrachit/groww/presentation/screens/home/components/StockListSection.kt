package com.devrachit.groww.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.devrachit.groww.R
import com.devrachit.groww.domain.models.Stock
import com.devrachit.groww.domain.models.StockType
import com.devrachit.groww.ui.theme.TextStyleInter18Lh24Fw700
import com.devrachit.groww.utility.composeUtility.sdp

@Composable
fun StockListSection(
    stockType: StockType,
    title: String,
    stockList: List<Stock>,
    isLoading: Boolean,
    onNavigationToDisplay: () -> Unit
) {
    when {
        !isLoading && stockList != emptyList<Stock>() -> {
            Text(
                text = title,
                modifier = Modifier.padding(top = 16.sdp, start = 16.sdp),
                color = colorResource(R.color.black),
                style = TextStyleInter18Lh24Fw700()
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.sdp, start = 8.sdp, end = 8.sdp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StockItem(
                    modifier = Modifier.weight(1f),
                    stock = stockList[0],
                    stockType = stockType,
                    onCompanyClick = {}
                )
                StockItem(
                    modifier = Modifier.weight(1f),
                    stock = stockList[1],
                    stockType = stockType,
                    onCompanyClick = {}
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.sdp, start = 8.sdp, end = 8.sdp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StockItem(
                    modifier = Modifier.weight(1f),
                    stock = stockList[2],
                    stockType = stockType,
                    onCompanyClick = {}
                )
                SeeMoreCard(
                    title1 = stockList[3].ticker,
                    title2 = stockList[4].ticker,
                    title3 = stockList[5].ticker,
                    title4 = stockList[6].ticker,
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigationToDisplay() }
                )
            }
        }
        isLoading -> {
            HomeScreenShimmer()
        }
        else -> {
            // Empty state
        }
    }
}