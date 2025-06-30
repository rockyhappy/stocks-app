package com.devrachit.groww.presentation.screens.details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.devrachit.groww.R
import com.devrachit.groww.data.local.entity.WatchlistEntity
import com.devrachit.groww.ui.theme.TextStyleInter14Lh20Fw600
import com.devrachit.groww.utility.composeUtility.sdp


@Composable
fun CheckWatchlistItemCard(
    watchlistEntity: WatchlistEntity,
    deleteWatchlist: (watchlistEntity: WatchlistEntity) -> Unit,
    onWatchlistEntry: (watchlistEntry: String) -> Unit,
    onCheckedChange: (isChecked: Boolean) -> Unit,
    isChecked: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.sdp, vertical = 8.sdp),
        shape = RoundedCornerShape(8.sdp),
        elevation = 4.sdp,
        backgroundColor = colorResource(R.color.card_elevated)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = colorResource(R.color.black),
                    uncheckedColor = colorResource(R.color.black),
                    checkmarkColor = colorResource(R.color.white),
                )
            )
            Text(
                text = watchlistEntity.name + " (" + watchlistEntity.count + ")",
                color = colorResource(R.color.black),
                style = TextStyleInter14Lh20Fw600(),
                modifier = Modifier.padding(16.sdp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.ic_delete_outline),
                contentDescription = "Error",
                tint = colorResource(R.color.black),
                modifier = Modifier
                    .padding(end = 12.sdp)
                    .size(24.sdp)
                    .clickable { deleteWatchlist(watchlistEntity) }
            )
            Icon(
                painter = painterResource(R.drawable.ic_right_arrow),
                contentDescription = "Error",
                tint = colorResource(R.color.black),
                modifier = Modifier
                    .size(12.sdp)
                    .clickable { onWatchlistEntry("") }
            )
        }
    }
}
