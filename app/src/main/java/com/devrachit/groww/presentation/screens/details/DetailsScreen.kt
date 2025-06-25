package com.devrachit.groww.presentation.screens.details

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


@Composable
fun DetailsScreen(
    title : String,
){
    Scaffold(
        modifier = Modifier.systemBarsPadding().fillMaxSize().background(Color.Blue),
        containerColor = Color.Blue
    ) {
        Text(text = title,
            modifier = Modifier.systemBarsPadding().padding(it)
        )
    }

}