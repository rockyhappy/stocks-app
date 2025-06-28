package com.devrachit.groww.presentation.screens.watchlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.devrachit.groww.utility.composeUtility.sdp

@Composable
fun WatchlistScreen(
    title: String,
    onNavigateToDetail: (ticker: String) -> Unit,
    onNavigationToDisplay: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.sdp)
        ){
            Text(text = title)
            Button(
                onClick = {},
                modifier = Modifier.padding(top = 16.sdp),
                enabled = true,
                content = {
                    Text(text = "Navigate to Detail")
                }
            )
            Button(
                onClick = {onNavigationToDisplay()},
                modifier = Modifier.padding(top = 16.sdp),
                enabled = true,
                content = {
                    Text(text = "Navigate to Display")
                }
            )

        }
    }
}