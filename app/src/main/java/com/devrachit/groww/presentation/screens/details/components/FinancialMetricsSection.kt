package com.devrachit.groww.presentation.screens.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.devrachit.groww.R
import com.devrachit.groww.ui.theme.TextStyleInter12Lh16Fw400

@Composable
fun FinancialMetricsSection(
    marketCap: String,
    peRatio: String,
    pegRatio: String,
    beta: String,
    bookValue: String,
    dividendPerShare: String,
    returnOnAssets: String,
    returnOnEquity: String
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Market\nCap\n$$marketCap",
            modifier = Modifier.padding(),
            color = colorResource(R.color.black),
            style = TextStyleInter12Lh16Fw400(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            textAlign = TextAlign.Center
        )
        Text(
            text = "P/E\nRatio\n$$peRatio",
            modifier = Modifier.padding(),
            color = colorResource(R.color.black),
            style = TextStyleInter12Lh16Fw400(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            textAlign = TextAlign.Center
        )
        Text(
            text = "PEG\nRatio\n$$pegRatio",
            modifier = Modifier.padding(),
            color = colorResource(R.color.black),
            style = TextStyleInter12Lh16Fw400(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Beta\n$$beta",
            modifier = Modifier.padding(),
            color = colorResource(R.color.black),
            style = TextStyleInter12Lh16Fw400(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            textAlign = TextAlign.Center
        )
    }
    
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Book\nValue\n$$bookValue",
            modifier = Modifier.padding(),
            color = colorResource(R.color.black),
            style = TextStyleInter12Lh16Fw400(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Dividend\nper Share\n$$dividendPerShare",
            modifier = Modifier.padding(),
            color = colorResource(R.color.black),
            style = TextStyleInter12Lh16Fw400(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Return\non Assets\n$$returnOnAssets",
            modifier = Modifier.padding(),
            color = colorResource(R.color.black),
            style = TextStyleInter12Lh16Fw400(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Return\non Equity\n$$returnOnEquity",
            modifier = Modifier.padding(),
            color = colorResource(R.color.black),
            style = TextStyleInter12Lh16Fw400(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            textAlign = TextAlign.Center
        )
    }
}