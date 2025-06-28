package com.devrachit.groww.presentation.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import com.devrachit.groww.R
import com.devrachit.groww.domain.models.Stock
import com.devrachit.groww.domain.models.StockType
import com.devrachit.groww.ui.theme.TextStyleInter12Lh16Fw400
import com.devrachit.groww.ui.theme.TextStyleInter12Lh16Fw600
import com.devrachit.groww.ui.theme.TextStyleInter14Lh20Fw600
import com.devrachit.groww.ui.theme.TextStyleTangerine14Lh20Fw400
import com.devrachit.groww.utility.composeUtility.sdp


@Composable
fun SeeMoreCard(
    modifier: Modifier = Modifier,
    title1: String,
    title2: String,
    title3: String,
    title4: String,
    onClick :()->Unit = {}
) {
    Card (
        modifier = modifier
            .padding(8.sdp)
            .height(140.sdp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.sdp),
        elevation = 2.sdp,
        backgroundColor = colorResource(R.color.card_elevated),
        border = BorderStroke(width = 1.sdp, color = colorResource(R.color.card_elevated_twice).copy(alpha=0.2f))
    ){
        Column(
            modifier = Modifier
                .padding(top=16.sdp, start = 16.sdp, end = 16.sdp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.sdp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .wrapContentWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.sdp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Logo(name = title1, modifier = Modifier.size(30.sdp))
                Logo(name = title2, modifier = Modifier.size(30.sdp))
            }

            Row(
                modifier = Modifier
                    .wrapContentWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.sdp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Logo(name = title3, modifier = Modifier.size(30.sdp))
                Logo(name = title4, modifier = Modifier.size(30.sdp))
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.padding(start=8.sdp, bottom=12.sdp),
                text="See More ->",
                color= colorResource(R.color.black),
                style = TextStyleInter12Lh16Fw600(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
