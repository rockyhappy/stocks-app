package com.devrachit.groww.presentation.screens.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.devrachit.groww.R
import com.devrachit.groww.ui.theme.TextStyleInter12Lh16Fw400
import com.devrachit.groww.utility.composeUtility.sdp

@Composable
fun WeekRangeIndicator(
    weekLow52: String,
    weekHigh52: String,
    currentPrice: String
) {
    val weekLow = weekLow52.toFloatOrNull() ?: 0f
    val weekHigh = weekHigh52.toFloatOrNull() ?: 1f
    val current = currentPrice.toFloatOrNull() ?: 0f
    val progress = ((current - weekLow) / (weekHigh - weekLow)).coerceIn(0f, 1f)
    val dynamicPaddingStart = with(LocalDensity.current) { 
        (progress * (LocalConfiguration.current.screenWidthDp - 120)).dp 
    }

    Row(
        modifier = Modifier
            .padding(10.sdp)

            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "52 Week\nLow\n$$weekLow52",
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
        ) {
            Text(
                text = "Current\n$$currentPrice\n↓",
                modifier = Modifier.padding(start = dynamicPaddingStart),
                color = colorResource(R.color.black),
                style = TextStyleInter12Lh16Fw400(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier
                    .padding(start = 10.sdp, end = 10.sdp)
                    .fillMaxWidth()
                    .height(10.sdp)
                    .clip(RoundedCornerShape(20.sdp))
                    .background(color = colorResource(R.color.easy_filled_blue))
            )
        }
        Text(
            text = "52 Week\nHigh\n$$weekHigh52",
            modifier = Modifier.padding(),
            color = colorResource(R.color.black),
            style = TextStyleInter12Lh16Fw400(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            textAlign = TextAlign.Center
        )
    }
}
