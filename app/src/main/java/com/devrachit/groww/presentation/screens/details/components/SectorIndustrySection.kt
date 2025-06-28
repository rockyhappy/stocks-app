package com.devrachit.groww.presentation.screens.details.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import com.devrachit.groww.R
import com.devrachit.groww.ui.theme.TextStyleInter10Lh12Fw400
import com.devrachit.groww.ui.theme.TextStyleInter12Lh16Fw700
import com.devrachit.groww.utility.composeUtility.sdp

@Composable
fun SectorIndustrySection(
    symbol: String,
    industry: String,
    sector: String
) {
    Column {
        Text(
            text = "Sectors and Industry: $symbol",
            modifier = Modifier.padding(start = 16.sdp, top = 16.sdp),
            color = colorResource(R.color.black),
            style = TextStyleInter12Lh16Fw700(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Column {
            Text(
                text = "Industry: $industry",
                modifier = Modifier
                    .padding(10.sdp)
                    .widthIn(max = 200.sdp)
                    .border(
                        border = BorderStroke(
                            width = 2.sdp,
                            color = colorResource(R.color.black).copy(alpha = 0.2f)
                        ), shape = RoundedCornerShape(16.sdp)
                    )
                    .padding(horizontal = 10.sdp, vertical = 5.sdp),
                color = colorResource(R.color.black),
                style = TextStyleInter10Lh12Fw400(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = "Sector: $sector",
                modifier = Modifier
                    .widthIn(max = 200.sdp)
                    .padding(10.sdp)
                    .border(
                        border = BorderStroke(
                            width = 2.sdp,
                            color = colorResource(R.color.black).copy(alpha = 0.2f)
                        ), shape = RoundedCornerShape(16.sdp)
                    )
                    .padding(horizontal = 10.sdp, vertical = 5.sdp),
                color = colorResource(R.color.black),
                style = TextStyleInter10Lh12Fw400(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}