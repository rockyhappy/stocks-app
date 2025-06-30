package com.devrachit.groww.presentation.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.devrachit.groww.R
import com.devrachit.groww.utility.composeUtility.sdp
import com.valentinilk.shimmer.shimmer

@Composable
fun StockItemShimmer(
    modifier: Modifier = Modifier,
) {
    androidx.compose.material.Card(
        modifier = modifier
            .padding(8.sdp)
            .fillMaxWidth()
            .height(150.sdp),
        shape = RoundedCornerShape(16.sdp),
        elevation = 2.sdp,
        backgroundColor = colorResource(R.color.card_elevated),
        border = BorderStroke(
            width = 1.sdp,
            color = colorResource(R.color.card_elevated_twice).copy(alpha = 0.2f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Logo shimmer
            Box(
                modifier = Modifier
                    .padding(start = 16.sdp, top = 16.sdp, bottom = 8.sdp)
                    .size(30.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )

            // Ticker text shimmer
            Box(
                modifier = Modifier
                    .padding(start = 16.sdp)
                    .fillMaxWidth(0.3f)
                    .height(20.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )
            
            androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(16.sdp))
            
            // Price text shimmer
            Box(
                modifier = Modifier
                    .padding(start = 16.sdp)
                    .fillMaxWidth(0.25f)
                    .height(20.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )

            // Change amount/percentage shimmer
            Box(
                modifier = Modifier
                    .padding(start = 16.sdp, bottom = 12.sdp, top = 8.sdp)
                    .fillMaxWidth(0.4f)
                    .height(16.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )
        }
    }
}

@Preview
@Composable
fun StockItemShimmerPreview() {
    StockItemShimmer()
}