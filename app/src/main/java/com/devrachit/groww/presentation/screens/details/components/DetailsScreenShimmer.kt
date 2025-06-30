package com.devrachit.groww.presentation.screens.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.devrachit.groww.R
import com.devrachit.groww.utility.composeUtility.sdp
import com.valentinilk.shimmer.shimmer

@Composable
fun DetailsScreenShimmer(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(start = 16.sdp, end = 16.sdp)
    ) {
        // Company Info Header Shimmer
        CompanyInfoHeaderShimmer()
        
        Spacer(modifier = Modifier.height(16.sdp))
        
        // Price Info Section Shimmer
        PriceInfoSectionShimmer()
        
        Spacer(modifier = Modifier.height(16.sdp))
        
        // Stock Chart Shimmer
        StockChartShimmer()
        
        Spacer(modifier = Modifier.height(16.sdp))
        
        // About Section Shimmer
        AboutSectionShimmer()
        
        Spacer(modifier = Modifier.height(16.sdp))
        
        // Sector Industry Section Shimmer
        SectorIndustrySectionShimmer()
        
        Divider(
            color = colorResource(R.color.black).copy(alpha = 0.1f),
            modifier = Modifier.padding(vertical = 10.sdp)
        )
        
        // Week Range Indicator Shimmer
        WeekRangeIndicatorShimmer()
        
        Divider(
            color = colorResource(R.color.black).copy(alpha = 0.1f),
            modifier = Modifier.padding(vertical = 10.sdp)
        )
        
        // Financial Metrics Section Shimmer
        FinancialMetricsSectionShimmer()
    }
}

@Composable
private fun CompanyInfoHeaderShimmer() {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Logo shimmer
            Box(
                modifier = Modifier
                    .size(48.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )
            
            Spacer(modifier = Modifier.width(12.sdp))
            
            Column {
                // Company name shimmer
                Box(
                    modifier = Modifier
                        .width(150.sdp)
                        .height(20.sdp)
                        .shimmer()
                        .clip(RoundedCornerShape(8.sdp))
                        .background(Color.Gray)
                )
                
                Spacer(modifier = Modifier.height(4.sdp))
                
                // Symbol and exchange shimmer
                Box(
                    modifier = Modifier
                        .width(100.sdp)
                        .height(16.sdp)
                        .shimmer()
                        .clip(RoundedCornerShape(8.sdp))
                        .background(Color.Gray)
                )
            }
        }
    }
}

@Composable
private fun PriceInfoSectionShimmer() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            // Target price label shimmer
            Box(
                modifier = Modifier
                    .width(80.sdp)
                    .height(14.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )
            
            Spacer(modifier = Modifier.height(4.sdp))
            
            // Target price value shimmer
            Box(
                modifier = Modifier
                    .width(60.sdp)
                    .height(18.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )
        }
        
        Column {
            // Dividend yield label shimmer
            Box(
                modifier = Modifier
                    .width(80.sdp)
                    .height(14.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )
            
            Spacer(modifier = Modifier.height(4.sdp))
            
            // Dividend yield value shimmer
            Box(
                modifier = Modifier
                    .width(50.sdp)
                    .height(18.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )
        }
    }
}

@Composable
private fun StockChartShimmer() {
    Column {
        // Chart buttons shimmer
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.sdp)
        ) {
            repeat(5) {
                Box(
                    modifier = Modifier
                        .width(40.sdp)
                        .height(32.sdp)
                        .shimmer()
                        .clip(RoundedCornerShape(8.sdp))
                        .background(Color.Gray)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.sdp))
        
        // Chart area shimmer
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.sdp)
                .shimmer()
                .clip(RoundedCornerShape(8.sdp))
                .background(Color.Gray)
        )
    }
}

@Composable
private fun AboutSectionShimmer() {
    Column {
        // About heading shimmer
        Box(
            modifier = Modifier
                .width(120.sdp)
                .height(20.sdp)
                .shimmer()
                .clip(RoundedCornerShape(8.sdp))
                .background(Color.Gray)
        )
        
        Spacer(modifier = Modifier.height(8.sdp))
        
        // Description lines shimmer
        repeat(3) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(if (it == 2) 0.7f else 1f)
                    .height(16.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.height(4.sdp))
        }
    }
}

@Composable
private fun SectorIndustrySectionShimmer() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            // Sector label shimmer
            Box(
                modifier = Modifier
                    .width(50.sdp)
                    .height(14.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )
            
            Spacer(modifier = Modifier.height(4.sdp))
            
            // Sector value shimmer
            Box(
                modifier = Modifier
                    .width(80.sdp)
                    .height(16.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )
        }
        
        Column {
            // Industry label shimmer
            Box(
                modifier = Modifier
                    .width(60.sdp)
                    .height(14.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )
            
            Spacer(modifier = Modifier.height(4.sdp))
            
            // Industry value shimmer
            Box(
                modifier = Modifier
                    .width(100.sdp)
                    .height(16.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )
        }
    }
}

@Composable
private fun WeekRangeIndicatorShimmer() {
    Column {
        // 52 Week Range label shimmer
        Box(
            modifier = Modifier
                .width(100.sdp)
                .height(16.sdp)
                .shimmer()
                .clip(RoundedCornerShape(8.sdp))
                .background(Color.Gray)
        )
        
        Spacer(modifier = Modifier.height(8.sdp))
        
        // Range indicator shimmer
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.sdp)
                .shimmer()
                .clip(RoundedCornerShape(12.sdp))
                .background(Color.Gray)
        )
        
        Spacer(modifier = Modifier.height(8.sdp))
        
        // Range values shimmer
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .width(60.sdp)
                    .height(14.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )
            
            Box(
                modifier = Modifier
                    .width(60.sdp)
                    .height(14.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )
        }
    }
}

@Composable
private fun FinancialMetricsSectionShimmer() {
    Column {
        repeat(4) { rowIndex ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(2) { colIndex ->
                    Column {
                        // Metric label shimmer
                        Box(
                            modifier = Modifier
                                .width(80.sdp)
                                .height(14.sdp)
                                .shimmer()
                                .clip(RoundedCornerShape(8.sdp))
                                .background(Color.Gray)
                        )
                        
                        Spacer(modifier = Modifier.height(4.sdp))
                        
                        // Metric value shimmer
                        Box(
                            modifier = Modifier
                                .width(60.sdp)
                                .height(16.sdp)
                                .shimmer()
                                .clip(RoundedCornerShape(8.sdp))
                                .background(Color.Gray)
                        )
                    }
                }
            }
            
            if (rowIndex < 3) {
                Spacer(modifier = Modifier.height(16.sdp))
            }
        }
    }
}

@Preview
@Composable
fun DetailsScreenShimmerPreview() {
    DetailsScreenShimmer()
}