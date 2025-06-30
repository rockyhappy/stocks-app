package com.devrachit.groww.presentation.screens.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devrachit.groww.R
import com.devrachit.groww.domain.models.SearchResult
import com.devrachit.groww.utility.composeUtility.sdp

@Composable
fun SearchResultItem(searchResult: SearchResult, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(searchResult.symbol)
            }
            .padding(vertical=4.sdp)
            .background(
                colorResource(R.color.card_elevated),
                RoundedCornerShape(8.dp)
            )
            .padding(vertical =8.sdp ,horizontal = 12.sdp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = searchResult.name,
                    color = colorResource(R.color.black),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = searchResult.symbol,
                    color = colorResource(R.color.black).copy(alpha = 0.7f),
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = searchResult.type.name,
                color = colorResource(R.color.black),
                fontSize = 12.sp,
                modifier = Modifier
                    .background(
                        colorResource(R.color.black).copy(alpha = 0.1f),
                        RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 8.sdp, vertical = 4.sdp)
            )
        }
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.sdp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${searchResult.region} • ${searchResult.currency}",
                color = colorResource(R.color.black).copy(alpha = 0.6f),
                fontSize = 12.sp
            )
            Text(
                text = "${searchResult.marketOpen} - ${searchResult.marketClose}",
                color = colorResource(R.color.black).copy(alpha = 0.6f),
                fontSize = 12.sp
            )
        }
    }
}