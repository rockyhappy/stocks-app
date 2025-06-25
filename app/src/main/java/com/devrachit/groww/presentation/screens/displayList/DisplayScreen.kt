package com.devrachit.groww.presentation.screens.displayList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DisplayListScreen(
    title: String,
    navigateToDetailsScreen: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize(),
        containerColor = Color.Cyan
    ) {
        androidx.compose.material3.Text(
            text = title,
            modifier = Modifier
                .systemBarsPadding()
                .padding(it)
        )
        androidx.compose.material3.Text(
            text = title,
            modifier = Modifier
                .systemBarsPadding()
                .padding()
                .clickable {
                    navigateToDetailsScreen()
                }
        )


    }
}