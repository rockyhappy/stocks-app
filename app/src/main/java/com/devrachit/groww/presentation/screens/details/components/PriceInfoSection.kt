package com.devrachit.groww.presentation.screens.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import com.devrachit.groww.R
import com.devrachit.groww.ui.theme.TextStyleInter12Lh16Fw400
import com.devrachit.groww.ui.theme.TextStyleInter16Lh24Fw700
import com.devrachit.groww.utility.composeUtility.sdp

@Composable
fun PriceInfoSection(
    analystTargetPrice: String,
    dividendYield: String
) {
    Column {
        Text(
            text = "Analyst Target Price: $$analystTargetPrice",
            modifier = Modifier.padding(start = 16.sdp, top = 16.sdp, end = 16.sdp),
            color = colorResource(R.color.black),
            style = TextStyleInter16Lh24Fw700(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Text(
            text = "Dividend Yield: $dividendYield",
            modifier = Modifier.padding(horizontal = 16.sdp, vertical = 0.sdp),
            color = colorResource(R.color.blue_notification),
            style = TextStyleInter12Lh16Fw400(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}