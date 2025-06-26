package com.devrachit.groww.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.devrachit.groww.R
import com.devrachit.groww.utility.composeUtility.sdp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    uiState: HomeScreenUiState,
    title: String,
    onNavigateToDetail: () -> Unit,
    onNavigationToDisplay: () -> Unit,
    onRefresh: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    )
    {
        val pullRefreshState = rememberPullRefreshState(
            refreshing = uiState.isLoading,
            onRefresh = {onRefresh()}
        )
       Column(
           modifier = Modifier
               .pullRefresh(pullRefreshState)
               .fillMaxSize()
               .verticalScroll(rememberScrollState())
               .background(color = colorResource(R.color.white))
               .padding(horizontal = 16.sdp)
       ){
           Text(text = uiState.toString())
           Button(
               onClick = {onNavigateToDetail()},
               modifier = Modifier.padding(top = 16.sdp),
               enabled = true,
               content = {
                   Text(text = "Navigate to Detail")
               }
           )
           Button(
               onClick = {onNavigationToDisplay()},
               modifier = Modifier.padding(top = 16.sdp),
               enabled = true,
               content = {
                   Text(text = "Navigate to Display")
               }
           )

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