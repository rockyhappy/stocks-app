package com.devrachit.groww.presentation.screens.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.devrachit.groww.R
import com.devrachit.groww.utility.composeUtility.sdp

@Composable
fun ProgressBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.sdp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = colorResource(R.color.black)
        )
    }
}