package com.devrachit.groww.presentation.screens.watchlist.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.devrachit.groww.R
import com.devrachit.groww.ui.theme.TextStyleInter24Lh36Fw700
import com.devrachit.groww.utility.composeUtility.sdp

@Composable
fun EmptyWatchlistState() {
    Icon(
        painter = painterResource(R.drawable.ic_empty),
        contentDescription = "No Data",
        tint = colorResource(R.color.black),
        modifier = Modifier
            .fillMaxWidth()
            .height(280.sdp)
            .padding(top = 16.sdp),
    )
    
    Text(
        text = "No Data",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.sdp, start = 16.sdp),
        color = colorResource(R.color.black),
        style = TextStyleInter24Lh36Fw700(),
        textAlign = TextAlign.Center
    )
}
