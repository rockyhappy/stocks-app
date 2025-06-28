package com.devrachit.groww.presentation.screens.home.components

import android.R.attr.fontFamily
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextOverflow
import com.devrachit.groww.R
import com.devrachit.groww.domain.models.Stock
import com.devrachit.groww.domain.models.StockType
import com.devrachit.groww.ui.theme.TextStyleInter12Lh16Fw400
import com.devrachit.groww.ui.theme.TextStyleInter12Lh16Fw600
import com.devrachit.groww.ui.theme.TextStyleInter12Lh16Fw700
import com.devrachit.groww.ui.theme.TextStyleInter14Lh20Fw600
import com.devrachit.groww.ui.theme.TextStyleInter16Lh24Fw400
import com.devrachit.groww.ui.theme.TextStyleInter16Lh24Fw500
import com.devrachit.groww.ui.theme.TextStyleInter16Lh24Fw700
import com.devrachit.groww.ui.theme.TextStyleInter20Lh24Fw400
import com.devrachit.groww.ui.theme.TextStyleInter20Lh24Fw500
import com.devrachit.groww.ui.theme.TextStyleInter20Lh24Fw600
import com.devrachit.groww.ui.theme.TextStyleTangerine14Lh20Fw400
import com.devrachit.groww.ui.theme.TextStyleTangerine16Lh20Fw400
import com.devrachit.groww.ui.theme.TextStyleTangerine20Lh26Fw400
import com.devrachit.groww.ui.theme.TextStyleTangerine24Lh30Fw400
import com.devrachit.groww.utility.composeUtility.sdp

@Composable
fun StockItem(
    modifier: Modifier = Modifier,
    stock: Stock,
    stockType: StockType,
    onCompanyClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(8.sdp)
            .height(140.sdp)
            .fillMaxWidth()
            .clickable(onClick = onCompanyClick),
        shape = RoundedCornerShape(16.sdp),
        elevation = 2.sdp,
        backgroundColor = colorResource(R.color.card_elevated),
        border = BorderStroke(
            width = 1.sdp,
            color = colorResource(R.color.card_elevated_twice).copy(alpha = 0.2f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Logo(
                modifier = Modifier.padding(start = 16.sdp, top = 16.sdp, bottom = 8.sdp).size(30.sdp),
                name = stock.ticker
            )

            Text(
                modifier = Modifier.padding(start = 16.sdp),
                text = stock.ticker,
                color = colorResource(R.color.black).copy(alpha = 0.8f),
                style = TextStyleTangerine14Lh20Fw400(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(16.sdp))
            Text(
                modifier = Modifier.padding(start = 16.sdp),
                text = "$${stock.price}",
                color = colorResource(R.color.black),
                style = TextStyleInter14Lh20Fw600(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )


            when (stockType) {
                is StockType.Gainer -> Text(
                    modifier = Modifier.padding(start = 16.sdp, bottom = 12.sdp),
                    text = "+${stock.changeAmount} (${stock.changePercentage})",
                    color = colorResource(R.color.green_normal_500),
                    style = TextStyleInter12Lh16Fw600(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                is StockType.Loser -> Text(
                    modifier = Modifier.padding(start = 16.sdp, bottom = 12.sdp),
                    text = "${stock.changeAmount} (${stock.changePercentage})",
                    color = colorResource(R.color.red_normal_500),
                    style = TextStyleInter12Lh16Fw600(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                is StockType.Active -> Text(
                    modifier = Modifier.padding(start = 16.sdp, bottom = 12.sdp),
                    text = "${stock.changeAmount} (${stock.changePercentage})",
                    color = colorResource(R.color.blue_normal_500),
                    style = TextStyleInter12Lh16Fw600(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
//            Text(
//                modifier = Modifier.padding(start=16.sdp, bottom=12.sdp),
//                text="Vol: ${stock.volume}",
//                color= colorResource(R.color.black),
//                style = TextStyleInter12Lh16Fw400(),
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis
//            )
        }
    }
}
