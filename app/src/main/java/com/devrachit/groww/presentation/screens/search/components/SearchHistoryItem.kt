package com.devrachit.groww.presentation.screens.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.devrachit.groww.R
import com.devrachit.groww.data.local.entity.SearchHistoryEntity
import com.devrachit.groww.utility.composeUtility.sdp

@Composable
fun SearchHistoryItem(searchHistory: SearchHistoryEntity, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.sdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.sdp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_history),
            contentDescription = null,
            modifier=Modifier.size(24.sdp)
        )
        Text(
            text = searchHistory.query,
            color = colorResource(R.color.black)
        )
    }
}