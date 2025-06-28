package com.devrachit.groww.presentation.screens.details.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.devrachit.groww.R
import com.devrachit.groww.presentation.screens.details.GraphState
import com.devrachit.groww.ui.theme.TextStyleInter14Lh20Fw600
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
    isLoading: Boolean
) {
    Box(
        modifier = Modifier
            .padding(vertical = 16.sdp)
            .fillMaxWidth()
            .height(400.sdp)
            .border(
                border = BorderStroke(
                    width = 1.sdp,
                    color = colorResource(R.color.black).copy(alpha = 0.2f)
                ), shape = RoundedCornerShape(16.sdp)
            )
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
                            
                            // Setup X-axis
                            xAxis.apply {
                                position = XAxis.XAxisPosition.BOTTOM
                                setDrawGridLines(false)
                                granularity = 1f
                                isGranularityEnabled = true
                            }
                            
                            // Setup Y-axis
                            axisLeft.apply {
                                setDrawGridLines(true)
                                gridColor = ContextCompat.getColor(context, R.color.black)
                                gridLineWidth = 0.5f
                            }
                            axisRight.isEnabled = false
                            
                            // Setup legend
                            legend.apply {
                                isEnabled = true
                                textColor = ContextCompat.getColor(context, R.color.black)
                            }
                        }
                    },
                    update = { chart ->
                        val sortedData = graphState.data.toList().sortedBy { it.first }
                        val timestamps = sortedData.map { it.first }
                        
                        val openEntries = mutableListOf<Entry>()
                        val highEntries = mutableListOf<Entry>()
                        val lowEntries = mutableListOf<Entry>()
                        val closeEntries = mutableListOf<Entry>()
                        val volumeEntries = mutableListOf<Entry>()
                        
                        sortedData.forEachIndexed { index, (_, ohlcvData) ->
                            val x = index.toFloat()
                            openEntries.add(Entry(x, ohlcvData.open.toFloatOrNull() ?: 0f))
                            highEntries.add(Entry(x, ohlcvData.high.toFloatOrNull() ?: 0f))
                            lowEntries.add(Entry(x, ohlcvData.low.toFloatOrNull() ?: 0f))
                            closeEntries.add(Entry(x, ohlcvData.close.toFloatOrNull() ?: 0f))
                            volumeEntries.add(Entry(x, (ohlcvData.volume.toFloatOrNull() ?: 0f) / 1000f))
                        }
                        
                        val chartLine1 = ContextCompat.getColor(chart.context, R.color.chart_line_1)
                        val chartLine2 = ContextCompat.getColor(chart.context, R.color.chart_line_2)
                        val chartLine3 = ContextCompat.getColor(chart.context, R.color.chart_line_3)
                        val chartLine4 = ContextCompat.getColor(chart.context, R.color.chart_line_4)
                        val chartLine5 = ContextCompat.getColor(chart.context, R.color.chart_line_5)

                        val openDataSet = LineDataSet(openEntries, "Open").apply {
                            color = chartLine1
                            setCircleColor(chartLine1)
                            lineWidth = 2f
                            circleRadius = 3f
                            setDrawCircleHole(false)
                            valueTextColor = ContextCompat.getColor(chart.context, R.color.black)
                            setDrawValues(false)
                        }
                        
                        val highDataSet = LineDataSet(highEntries, "High").apply {
                            color = chartLine2
                            setCircleColor(chartLine2)
                            lineWidth = 2f
                            circleRadius = 3f
                            setDrawCircleHole(false)
                            valueTextColor = ContextCompat.getColor(chart.context, R.color.black)
                            setDrawValues(false)
                        }
                        
                        val lowDataSet = LineDataSet(lowEntries, "Low").apply {
                            color = chartLine3
                            setCircleColor(chartLine3)
                            lineWidth = 2f
                            circleRadius = 3f
                            setDrawCircleHole(false)
                            valueTextColor = ContextCompat.getColor(chart.context, R.color.black)
                            setDrawValues(false)
                        }
                        
                        val closeDataSet = LineDataSet(closeEntries, "Close").apply {
                            color = chartLine4
                            setCircleColor(chartLine4)
                            lineWidth = 2f
                            circleRadius = 3f
                            setDrawCircleHole(false)
                            valueTextColor = ContextCompat.getColor(chart.context, R.color.black)
                            setDrawValues(false)
                        }
                        
                        val volumeDataSet = LineDataSet(volumeEntries, "Volume(K)").apply {
                            color = chartLine5
                            setCircleColor(chartLine5)
                            lineWidth = 2f
                            circleRadius = 3f
                            setDrawCircleHole(false)
                            valueTextColor = ContextCompat.getColor(chart.context, R.color.black)
                            setDrawValues(false)
                        }
                        
                        val lineData = LineData(openDataSet, highDataSet, lowDataSet, closeDataSet, volumeDataSet)
                        chart.data = lineData
                        
                        // Format X-axis labels with timestamps
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
                    modifier = Modifier.fillMaxSize().padding(8.dp)
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
    }
}