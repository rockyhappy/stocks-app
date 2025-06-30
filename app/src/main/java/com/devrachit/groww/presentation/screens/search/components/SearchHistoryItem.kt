package com.devrachit.groww.presentation.screens.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devrachit.groww.R
import com.devrachit.groww.data.local.entity.SearchHistoryEntity
import com.devrachit.groww.utility.composeUtility.sdp

@Composable
fun SearchHistoryItem(searchHistory: SearchHistoryEntity, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 4.sdp)
            .background(
                colorResource(R.color.card_elevated),
                RoundedCornerShape(8.dp)
            )
            .padding(vertical = 12.sdp, horizontal = 16.sdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.sdp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_history),
            contentDescription = null,
            modifier = Modifier.size(20.sdp),
            tint = colorResource(R.color.black).copy(alpha = 0.6f)
        )
        Text(
            text = searchHistory.query,
            color = colorResource(R.color.black),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}