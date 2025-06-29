package com.devrachit.groww.presentation.screens.details.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.devrachit.groww.R
import com.devrachit.groww.presentation.screens.details.GraphState
import com.devrachit.groww.presentation.screens.details.GraphType
import com.devrachit.groww.ui.theme.TextStyleInter12Lh16Fw500
import com.devrachit.groww.ui.theme.TextStyleInter14Lh20Fw600
import com.devrachit.groww.ui.theme.TextStyleInter16Lh24Fw600
import com.devrachit.groww.ui.theme.TextStyleInter18Lh24Fw700
import com.devrachit.groww.utility.composeUtility.sdp
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun StockChart(
    graphState: GraphState,
    isLoading: Boolean,
    onGraphTypeChange: (GraphType) -> Unit
) {
    var selectedDataType by remember { mutableStateOf(ChartDataType.OPEN) }
    
    Column {
        // Custom Legend
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.sdp, vertical = 8.sdp)
        ) {
            items(ChartDataType.values()) { dataType ->
                LegendItem(
                    dataType = dataType,
                    isSelected = selectedDataType == dataType,
                    onClick = { selectedDataType = dataType }
                )
                Spacer(modifier = Modifier.width(12.sdp))
            }
        }
        
        // Chart
        Column(
            modifier = Modifier
                .padding(vertical = 8.sdp, horizontal = 16.sdp)
                .fillMaxWidth()
                .height(500.sdp)
                .border(
                    border = BorderStroke(
                        width = 1.sdp,
                        color = colorResource(R.color.black).copy(alpha = 0.2f)
                    ), shape = RoundedCornerShape(16.sdp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            when {
                !isLoading && graphState.data.isNotEmpty() -> {
                    AndroidView(
                        factory = { context ->
                            LineChart(context).apply {
                                description.isEnabled = false
                                setTouchEnabled(true)
                                isDragEnabled = true
                                setScaleEnabled(true)
                                setPinchZoom(true)
                                setDrawGridBackground(false)

                                xAxis.apply {
                                    position = XAxis.XAxisPosition.BOTTOM
                                    setDrawGridLines(false)
                                    granularity = 1f
                                    isGranularityEnabled = true
                                    textSize = 8f
                                    labelRotationAngle = -45f
                                    textColor = ContextCompat.getColor(context, R.color.black)
                                }

                                axisLeft.apply {
                                    setDrawGridLines(true)
                                    gridColor = ContextCompat.getColor(context, R.color.black)
                                    gridLineWidth = 0.5f
                                    textColor = ContextCompat.getColor(context, R.color.black)
                                }
                                axisRight.isEnabled = false

                                // Disable default legend since we have custom one
                                legend.isEnabled = false
                            }
                        },
                        update = { chart ->
                            val sortedData = graphState.data.toList().sortedBy { it.first }
                            val timestamps = sortedData.map { it.first }

                            val entries = mutableListOf<Entry>()

                            sortedData.forEachIndexed { index, (_, ohlcvData) ->
                                val x = index.toFloat()
                                val y = when (selectedDataType) {
                                    ChartDataType.OPEN -> ohlcvData.open.toFloatOrNull() ?: 0f
                                    ChartDataType.HIGH -> ohlcvData.high.toFloatOrNull() ?: 0f
                                    ChartDataType.LOW -> ohlcvData.low.toFloatOrNull() ?: 0f
                                    ChartDataType.CLOSE -> ohlcvData.close.toFloatOrNull() ?: 0f
                                    ChartDataType.VOLUME -> (ohlcvData.volume.toFloatOrNull() ?: 0f) / 1000f
                                }
                                entries.add(Entry(x, y))
                            }

                            val lineColor = getColorForDataType(selectedDataType, chart.context)

                            val dataSet = LineDataSet(entries, selectedDataType.displayName).apply {
                                color = lineColor
                                setCircleColor(lineColor)
                                lineWidth = 3f
                                circleRadius = 4f
                                setDrawCircleHole(false)
                                valueTextColor = ContextCompat.getColor(chart.context, R.color.black)
                                setDrawValues(false)
                                setDrawFilled(false)
                            }

                            val lineData = LineData(dataSet)
                            chart.data = lineData

                            chart.xAxis.valueFormatter = IndexAxisValueFormatter(timestamps.map { timestamp ->
                                try {
                                    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
                                    sdf.format(java.util.Date(timestamp.toLong() * 1000))
                                } catch (e: Exception) {
                                    timestamp
                                }
                            })

                            chart.invalidate()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .padding(8.dp)
                    )


                }

                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Loading chart...",
                            color = colorResource(R.color.black),
                            style = TextStyleInter14Lh20Fw600()
                        )
                    }
                }

                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No chart data available",
                            color = colorResource(R.color.black),
                            style = TextStyleInter14Lh20Fw600()
                        )
                    }
                }

            }
            Row(
                modifier = Modifier.padding(top=20.sdp).wrapContentHeight().wrapContentHeight()
                    .border(
                        border = BorderStroke(
                            width = 1.sdp,
                            color = colorResource(R.color.black).copy(alpha = 0.2f)
                        ), shape = RoundedCornerShape(16.sdp)
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    text = "ID",
                    color = colorResource(R.color.black),
                    style = if(graphState.graphType==GraphType.INTRA_DAY) TextStyleInter18Lh24Fw700() else TextStyleInter14Lh20Fw600(),
                    modifier = Modifier.padding(vertical =8.dp,horizontal =12.dp).clickable { onGraphTypeChange(GraphType.INTRA_DAY)}
                )
                Text(
                    text = "D",
                    color = colorResource(R.color.black),
                    style = if(graphState.graphType==GraphType.DAILY) TextStyleInter18Lh24Fw700() else TextStyleInter14Lh20Fw600(),
                    modifier = Modifier.padding(vertical =8.dp,horizontal =12.dp).clickable { onGraphTypeChange(GraphType.DAILY)}
                )
                Text(
                    text = "W",
                    color = colorResource(R.color.black),
                    style = if(graphState.graphType==GraphType.WEEKLY) TextStyleInter18Lh24Fw700() else TextStyleInter14Lh20Fw600(),
                    modifier = Modifier.padding(vertical =8.dp,horizontal =12.dp).clickable { onGraphTypeChange(GraphType.WEEKLY)}
                )
                Text(
                    text = "M",
                    color = colorResource(R.color.black),
                    style = if(graphState.graphType==GraphType.MONTHLY) TextStyleInter18Lh24Fw700() else TextStyleInter14Lh20Fw600(),
                    modifier = Modifier.padding(vertical =8.dp,horizontal =12.dp).clickable { onGraphTypeChange(GraphType.MONTHLY)}
                )


            }
        }
    }
}

@Composable
private fun LegendItem(
    dataType: ChartDataType,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .background(
                color = if (isSelected) colorResource(R.color.groww_green).copy(alpha = 0.1f) 
                       else colorResource(R.color.white),
                shape = RoundedCornerShape(8.sdp)
            )
            .border(
                width = 1.sdp,
                color = if (isSelected) colorResource(R.color.groww_green) 
                       else colorResource(R.color.black).copy(alpha = 0.2f),
                shape = RoundedCornerShape(8.sdp)
            )
            .padding(horizontal = 12.sdp, vertical = 6.sdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(12.sdp)
                .clip(CircleShape)
                .background(colorResource(getColorResourceForDataType(dataType)))
        )
        Spacer(modifier = Modifier.width(6.sdp))
        Text(
            text = dataType.displayName,
            style = TextStyleInter12Lh16Fw500(),
            color = if (isSelected) colorResource(R.color.groww_green) 
                   else colorResource(R.color.black)
        )
    }
}

private fun getColorForDataType(dataType: ChartDataType, context: android.content.Context): Int {
    return when (dataType) {
        ChartDataType.OPEN -> ContextCompat.getColor(context, R.color.chart_line_1)
        ChartDataType.HIGH -> ContextCompat.getColor(context, R.color.chart_line_2)
        ChartDataType.LOW -> ContextCompat.getColor(context, R.color.chart_line_3)
        ChartDataType.CLOSE -> ContextCompat.getColor(context, R.color.chart_line_4)
        ChartDataType.VOLUME -> ContextCompat.getColor(context, R.color.chart_line_5)
    }
}

private fun getColorResourceForDataType(dataType: ChartDataType): Int {
    return when (dataType) {
        ChartDataType.OPEN -> R.color.chart_line_1
        ChartDataType.HIGH -> R.color.chart_line_2
        ChartDataType.LOW -> R.color.chart_line_3
        ChartDataType.CLOSE -> R.color.chart_line_4
        ChartDataType.VOLUME -> R.color.chart_line_5
    }
}
