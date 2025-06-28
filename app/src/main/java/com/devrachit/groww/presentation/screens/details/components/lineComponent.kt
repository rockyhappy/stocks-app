package com.devrachit.groww.presentation.screens.details.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.devrachit.groww.R
import com.devrachit.groww.utility.composeUtility.sdp


@Composable
fun PriceRangeIndicator(
    modifier: Modifier = Modifier,
    week52Low: Float,
    currentPrice: Float,
    week52High: Float
) {
    val progress = ((currentPrice - week52Low) / (week52High - week52Low)).coerceIn(0f, 1f)

    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(horizontalAlignment = Alignment.Start) {
                Text("52-Week Low",
                )
                Text("$${week52Low}",

                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Current price: $${currentPrice}",

                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text("52-Week High",

                )
                Text("$${week52High}",

             )
            }
        }

        Spacer(modifier = Modifier.height(12.sdp))


        val green = colorResource(id = R.color.groww_green)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.sdp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val lineY = size.height / 2
                val lineStart = Offset(x = 0f, y = lineY)
                val lineEnd = Offset(x = size.width, y = lineY)


                drawLine(
                    color = green,
                    start = lineStart,
                    end = lineEnd,
                    strokeWidth = 2.dp.toPx()
                )


                val triangleCenterX = size.width * progress
                val triangleHeight = 10.dp.toPx()
                val triangleWidth = 10.dp.toPx()

                drawPath(
                    path = Path().apply {
                        moveTo(triangleCenterX, lineY - triangleHeight) // top point
                        lineTo(triangleCenterX - triangleWidth / 2, lineY) // bottom left
                        lineTo(triangleCenterX + triangleWidth / 2, lineY) // bottom right
                        close()
                    },
                    color = green
                )
            }
        }
    }
}
