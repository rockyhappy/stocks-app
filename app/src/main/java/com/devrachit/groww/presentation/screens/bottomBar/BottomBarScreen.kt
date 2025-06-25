package com.devrachit.groww.presentation.screens.bottomBar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.devrachit.groww.utility.composeUtility.sdp


@Composable
fun BottomBarScreen(
    title: String,
    navigateToDetailsScreen: () -> Unit,
    navigateToDisplayScreen: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .background(Color.Green),
        containerColor = Color.Green
    ) {
        Text(
            text = title,
            modifier = Modifier
                .systemBarsPadding()
                .padding(it)
        )
        Text(
            text = title,
            modifier = Modifier
                .systemBarsPadding()
                .padding(top=100.sdp)
                .clickable {
                    navigateToDetailsScreen()
                }
        )
        Text(
            text = title,
            modifier = Modifier
                .systemBarsPadding()
                .padding(top=200.sdp)
                .clickable {
                    navigateToDisplayScreen()
                }
        )

    }
}