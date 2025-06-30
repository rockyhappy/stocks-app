package com.devrachit.groww.presentation.screens.watchlist.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.devrachit.groww.R
import com.devrachit.groww.ui.theme.TextStyleInter18Lh24Fw700
import com.devrachit.groww.utility.composeUtility.sdp

@Composable
fun WatchlistTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(top = 16.sdp, start = 16.sdp),
        color = colorResource(R.color.black),
        style = TextStyleInter18Lh24Fw700()
    )
}
