package com.devrachit.groww.presentation.screens.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devrachit.groww.R
import com.devrachit.groww.domain.models.FilterType
import com.devrachit.groww.presentation.screens.search.components.ProgressBar
import com.devrachit.groww.presentation.screens.search.components.SearchHistoryItem
import com.devrachit.groww.presentation.screens.search.components.SearchResultItem
import com.devrachit.groww.presentation.screens.search.components.SearchTextField
import com.devrachit.groww.utility.composeUtility.sdp
import kotlinx.coroutines.android.awaitFrame

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    searchText: String,
    uiState: SearchScreenUIState,
    filterType: FilterType,
    onSearchTextChange: (String) -> Unit,
    onClearSearchText: () -> Unit,
    onAddToSearchHistory: () -> Unit,
    onUpdateFilter: (FilterType) -> Unit,
    onResetErrorMessage: () -> Unit,
    navigateTo: (String) -> Unit,
    navigateBack: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val filteredSearchResults by remember(uiState.searchResults, filterType) {
        derivedStateOf {
            if (filterType == FilterType.None)
                uiState.searchResults
            else
                uiState.searchResults.filter { it.type == filterType }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        containerColor = colorResource(R.color.white)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Header Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(R.color.white))
                    .padding(top = 16.sdp, bottom = 8.sdp)
            ) {
                SearchTextField(
                    value = searchText,
                    onValueChange = onSearchTextChange,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    navigateBack()
                                },
                            tint = colorResource(R.color.black)
                        )
                    },
                    trailingIcon = {
                        if (searchText.isNotEmpty()) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear",
                                modifier = Modifier.clickable {
                                    onClearSearchText()
                                },
                                tint = colorResource(R.color.black).copy(alpha = 0.7f)
                            )
                        }
                    },
                    modifier = Modifier.focusRequester(focusRequester),
                    onSearchClick = {
                        focusManager.clearFocus()
                        onAddToSearchHistory()
                    }
                )

                // Filter Chips
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.sdp, vertical = 8.sdp),
                    horizontalArrangement = Arrangement.spacedBy(8.sdp),
                    verticalArrangement = Arrangement.spacedBy(4.sdp)
                ) {
                    FilterType.entries.reversed().forEach { type ->
                        FilterChip(
                            selected = filterType == type,
                            onClick = {
                                onUpdateFilter(type)
                            },
                            label = {
                                Text(
                                    text = type.text,
                                    color = if (filterType == type) 
                                        colorResource(R.color.white) 
                                    else 
                                        colorResource(R.color.black)
                                )
                            },
                            colors = androidx.compose.material3.FilterChipDefaults.filterChipColors(
                                selectedContainerColor = colorResource(R.color.black),
                                selectedLabelColor = colorResource(R.color.white),
                                containerColor = colorResource(R.color.card_elevated),
                                labelColor = colorResource(R.color.black)
                            )
                        )
                    }
                }
            }

            // Content Section
            if (uiState.isLoading) {
                ProgressBar()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(R.color.white)),
                    contentPadding = PaddingValues(horizontal = 16.sdp, vertical = 8.sdp),
                    verticalArrangement = Arrangement.spacedBy(4.sdp)
                ) {
                    if (filteredSearchResults.isEmpty() && searchText.isNotEmpty()) {
                        item {
                            Text(
                                text = "No results found",
                                color = colorResource(R.color.black).copy(alpha = 0.6f),
                                modifier = Modifier.padding(16.sdp)
                            )
                        }
                    } else if (filteredSearchResults.isEmpty()) {
                        if (uiState.searchHistory.isNotEmpty()) {
                            item {
                                Text(
                                    text = "Recent Searches",
                                    color = colorResource(R.color.black),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(vertical = 12.sdp, horizontal = 4.sdp)
                                )
                            }
                        }
                        items(
                            items = uiState.searchHistory,
                            key = { it.id }
                        ) { historyItem ->
                            SearchHistoryItem(historyItem) {
                                onSearchTextChange(historyItem.query)
                            }
                        }
                    } else {
                        item {
                            Text(
                                text = "Search Results",
                                color = colorResource(R.color.black),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = 12.sdp, horizontal = 4.sdp)
                            )
                        }
                        items(
                            items = filteredSearchResults,
                            key = { it.symbol }
                        ) { searchResult ->
                            SearchResultItem(searchResult) { symbol ->
                                onAddToSearchHistory()
                                navigateTo(symbol)
                            }
                        }
                    }
                }
            }

            if (!uiState.error.isNullOrBlank()) {
                LaunchedEffect(uiState.error) {
                    snackbarHostState.showSnackbar(
                        message = uiState.error!!
                    )
                    onResetErrorMessage()
                }
            }

            LaunchedEffect(focusRequester) {
                awaitFrame()
                focusRequester.requestFocus()
            }
        }
    }
}
