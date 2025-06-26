package com.devrachit.groww.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.devrachit.groww.ui.theme.TextStyleTangerine20Lh26Fw400
import com.devrachit.groww.utility.composeUtility.sdp


@Composable
fun Logo(
    modifier: Modifier = Modifier,
    name: String) {
//    val pastelColors = listOf(
//        Color(0xFFFFC1CC), // Pastel Pink
//        Color(0xFFAEDFF7), // Pastel Blue
//        Color(0xFFB5EAD7), // Pastel Green
//        Color(0xFFFFDAC1), // Pastel Peach
//        Color(0xFFF5CBA7), // Pastel Orange
//        Color(0xFFD7BDE2), // Pastel Purple
//        Color(0xFFF9F3CC)  // Pastel Yellow
//    )
    val pastelColors = listOf(
        Color(0xFFFFAACC), // Bright Pastel Pink
        Color(0xFF8EE3F5), // Bright Pastel Cyan
        Color(0xFF98F5E1), // Bright Mint Green
        Color(0xFFFFD580), // Bright Pastel Orange/Peach
        Color(0xFFE0BBE4), // Bright Lavender
        Color(0xFFFFF48F), // Bright Pastel Yellow
        Color(0xFFA0E7E5)  // Bright Aqua
    )

    Box(
        modifier = modifier
            .size(30.sdp)
            .background(color = pastelColors.random(), shape = RoundedCornerShape(8.sdp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name.first().toString(),
            style = TextStyleTangerine20Lh26Fw400(),
            color= Color.Black
        )
    }
}