package com.devrachit.groww.presentation.screens.watchlist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.devrachit.groww.R
import com.devrachit.groww.ui.theme.TextStyleInter14Lh20Fw600
import com.devrachit.groww.ui.theme.TextStyleInter18Lh24Fw700
import com.devrachit.groww.utility.composeUtility.sdp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchlistEntryForm(
    watchlistEntry: String?,
    isWatchlistError: Boolean,
    onWatchlistEntry: (watchlistEntry: String) -> Unit,
    addWatchlist: (name: String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 16.sdp)
    ) {
        OutlinedTextField(
            value = watchlistEntry ?: "",
            onValueChange = { onWatchlistEntry(it) },
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.sdp, end = 16.sdp),
            placeholder = {
                Text(
                    text = "New Watchlist Name",
                    color = colorResource(R.color.black),
                    style = TextStyleInter14Lh20Fw600()
                )
            },
            shape = RoundedCornerShape(16.sdp),
            singleLine = true,
            isError = isWatchlistError,
            trailingIcon = {
                if (watchlistEntry?.isNotEmpty() == true)
                    Icon(
                        painter = painterResource(R.drawable.ic_cross),
                        contentDescription = "Error",
                        tint = colorResource(R.color.black),
                        modifier = Modifier
                            .size(16.sdp)
                            .clickable { onWatchlistEntry("") }
                    )
            },
            colors = TextFieldDefaults.colors(
                cursorColor = colorResource(R.color.black),
                focusedIndicatorColor = colorResource(R.color.black),
                unfocusedIndicatorColor = colorResource(R.color.black),
                disabledIndicatorColor = colorResource(R.color.black),
                focusedTextColor = colorResource(R.color.black),
                unfocusedTextColor = colorResource(R.color.black),
                disabledTextColor = colorResource(R.color.black),
                focusedContainerColor = colorResource(R.color.card_elevated),
                unfocusedContainerColor = colorResource(R.color.card_elevated),
            )
        )

        Button(
            onClick = {
                if (watchlistEntry != null && watchlistEntry.isNotEmpty() == true) {
                    addWatchlist(watchlistEntry.toString())
                    onWatchlistEntry("")
                }
            },
            shape = RoundedCornerShape(16.sdp),
            modifier = Modifier
                .padding(end = 16.sdp),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 10.sdp,
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.card_elevated),
            )
        ) {
            Text(
                text = "Add",
                modifier = Modifier.padding(8.sdp),
                color = colorResource(R.color.black),
                style = TextStyleInter18Lh24Fw700()
            )
        }
    }
}
