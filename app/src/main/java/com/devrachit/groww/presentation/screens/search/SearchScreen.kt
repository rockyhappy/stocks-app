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
import androidx.compose.ui.unit.dp
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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = colorResource(R.color.white))
                .padding(vertical = 16.sdp)
        ) {
            SearchTextField(
                value = searchText,
                onValueChange = onSearchTextChange,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
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
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                onClearSearchText()
                            },
                            tint = colorResource(R.color.black)
                        )
                    }
                },
                modifier = Modifier.focusRequester(focusRequester),
            ) {
                focusManager.clearFocus()
                onAddToSearchHistory()
            }

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
                                color = colorResource(R.color.black)
                            )
                        }
                    )
                }
            }
//            Text(text=filteredSearchResults.toString(),color=colorResource(R.color.black))
            if (uiState.isLoading) {
                ProgressBar()
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.sdp)
                ) {
                    if (filteredSearchResults.isEmpty()) {
                        items(
                            items = uiState.searchHistory,
                            key = { it.id }
                        ) { historyItem ->
                            SearchHistoryItem(historyItem) {
                                onSearchTextChange(historyItem.query)
                            }
                        }
                    } else {
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
