package com.devrachit.groww.presentation.screens.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import com.devrachit.groww.R
import com.devrachit.groww.presentation.screens.home.components.Logo
import com.devrachit.groww.ui.theme.TextStyleInter12Lh16Fw400
import com.devrachit.groww.ui.theme.TextStyleInter12Lh16Fw500
import com.devrachit.groww.ui.theme.TextStyleInter12Lh16Fw700
import com.devrachit.groww.utility.composeUtility.sdp

@Composable
fun CompanyInfoHeader(
    ticker: String,
    companyName: String,
    symbol: String,
    assetType: String,
    exchange: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Logo(
            name = ticker,
            modifier = Modifier
                .clip(CircleShape)
                .size(46.sdp)
        )
        Column(
            modifier = Modifier
                .padding(start = 16.sdp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = companyName,
                modifier = Modifier.padding(),
                color = colorResource(R.color.black),
                style = TextStyleInter12Lh16Fw700(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = "$symbol, $assetType",
                modifier = Modifier.padding(),
                color = colorResource(R.color.black),
                style = TextStyleInter12Lh16Fw400(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = exchange,
                modifier = Modifier.padding(),
                color = colorResource(R.color.black),
                style = TextStyleInter12Lh16Fw500(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}