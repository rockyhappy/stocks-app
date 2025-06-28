package com.devrachit.groww.presentation.screens.watchlist


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.devrachit.groww.R
import com.devrachit.groww.data.local.entity.WatchlistEntity
import com.devrachit.groww.domain.models.Stock
import com.devrachit.groww.ui.theme.TextStyleInter14Lh20Fw600
import com.devrachit.groww.ui.theme.TextStyleInter18Lh24Fw700
import com.devrachit.groww.ui.theme.TextStyleInter24Lh36Fw700
import com.devrachit.groww.utility.composeUtility.sdp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun WatchlistScreen(
    uiState: WatchlistScreenUiStates,
    title: String,
    onNavigateToDetail: (stock: Stock) -> Unit,
    onNavigationToDisplay: () -> Unit,
    onRefresh: () -> Unit,
    onWatchlistEntry: (watchlistEntry: String) -> Unit,
    addWatchlist: (name: String) -> Unit,
    deleteWatchlist: (watchlistEntity: WatchlistEntity) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    )
    {
        val pullRefreshState = rememberPullRefreshState(
            refreshing = uiState.isLoading,
            onRefresh = { onRefresh() }
        )



        LazyColumn(
            modifier = Modifier
//                .pullRefresh(pullRefreshState)
                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
                .background(color = colorResource(R.color.white)),

            )
        {
            item {
                Text(
                    text = title,
                    modifier = Modifier.padding(top = 16.sdp, start = 16.sdp),
                    color = colorResource(R.color.black),
                    style = TextStyleInter18Lh24Fw700()
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 16.sdp)
                ) {
                    OutlinedTextField(
                        value = uiState.watchlistEntry ?: "",
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
                        isError = uiState.isWatchlistError,
//                        leadingIcon = {
//                            if (uiState.isWatchlistError)
//                                Icon(
//                                    painter = painterResource(R.drawable.ic_error),
//                                    contentDescription = "Error",
//                                    modifier = Modifier.size(24.sdp),
//                                    tint = colorResource(R.color.black)
//                                )
//                        },
                        trailingIcon = {
                            if (uiState.watchlistEntry?.isNotEmpty() == true)
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
                            if (uiState.watchlistEntry != null && uiState.watchlistEntry.isNotEmpty() == true) {
                                addWatchlist(uiState.watchlistEntry.toString())
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
            if (uiState.allWatchlist.isEmpty() && uiState.isLoading.not()) {
                item {
                    Icon(
                        painter = painterResource(R.drawable.ic_empty),
                        contentDescription = "No Data",
                        tint = colorResource(R.color.black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.sdp)
                            .padding(top = 16.sdp),
                    )
                }
                item {
                    Text(
                        text = "No Data",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.sdp, start = 16.sdp),
                        color = colorResource(R.color.black),
                        style = TextStyleInter24Lh36Fw700(),
                        textAlign = TextAlign.Center
                    )
                }
            }

            items(uiState.allWatchlist.size) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.sdp, vertical = 8.sdp),
                    shape = RoundedCornerShape(8.sdp),
                    elevation = 4.sdp,
                    backgroundColor = colorResource(R.color.card_elevated)
                )
                {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.sdp),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Text(
                            text = uiState.allWatchlist[index].name + " (" + uiState.allWatchlist[index].count+")",
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
                                .clickable { deleteWatchlist(uiState.allWatchlist[index]) }
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
        }


        PullRefreshIndicator(
            refreshing = uiState.isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            backgroundColor = colorResource(id = R.color.white),
            contentColor = colorResource(id = R.color.black)
        )
    }
}