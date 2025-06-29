package com.devrachit.groww.presentation.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.devrachit.groww.utility.composeUtility.sdp
import com.valentinilk.shimmer.shimmer
import com.devrachit.groww.R


@Composable
fun HomeScreenShimmer(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(400.sdp)
            .clip(RoundedCornerShape(10.sdp))
    ) {
        Box(
            modifier = Modifier
                .padding(top = 16.sdp, start = 16.sdp, end = 16.sdp)
                .width(200.sdp)
                .height(30.sdp)
                .shimmer()
                .clip(RoundedCornerShape(16.sdp))
                .background(Color.Gray)
        )
        Row(
            modifier = Modifier
                .padding(top = 16.sdp, start = 16.sdp, end = 16.sdp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween

        )
        {
            StockItemShimmer(
                modifier = Modifier
                    .weight(1f)
            )
            StockItemShimmer(
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 16.sdp, start = 16.sdp, end = 16.sdp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween

        )
        {
            StockItemShimmer(
                modifier = Modifier
                    .weight(1f)
            )
            StockItemShimmer(
                modifier = Modifier
                    .weight(1f)
            )
        }

        
    }
}


@Preview
@Composable
fun HomeScreenShimmerPreview() {
    HomeScreenShimmer()
}
