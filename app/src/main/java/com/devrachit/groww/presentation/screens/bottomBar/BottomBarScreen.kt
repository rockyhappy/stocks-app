package com.devrachit.groww.presentation.screens.bottomBar

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devrachit.groww.R
import com.devrachit.groww.presentation.navigation.NavGraph
import com.devrachit.groww.presentation.navigation.Screen
import com.devrachit.groww.presentation.navigation.navigateToTab
import com.devrachit.groww.presentation.navigation.rememberNavigationItems
import com.devrachit.groww.ui.theme.TextStyleInter20Lh24Fw600
import com.devrachit.groww.utility.composeUtility.NavItem
import com.devrachit.groww.utility.composeUtility.ThemeSelector
import com.devrachit.groww.utility.composeUtility.sdp
import com.devrachit.groww.utility.constants.Constants.Companion.APP_TITLE
import com.devrachit.groww.utility.constants.Constants.Companion.START_DESTINATION_INNER_NAV


@Composable
fun BottomBarScreen(
    title: String,
    navigateToDetailsScreen: () -> Unit,
    navigateToDisplayScreen: () -> Unit,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val navItems = rememberNavigationItems()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.HomeScreen.route

    var showExitDialog by remember { mutableStateOf(false) }

    BackHandler(enabled = currentRoute == Screen.HomeScreen.route) {
        showExitDialog = true
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(colorResource(id = R.color.white))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(56.sdp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(start = 24.sdp)
                    .size(40.sdp)
                    .border(
                        border = BorderStroke(width=3.sdp, brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFFFBBF24), Color(0xFFF59E0B), Color(0xFFD97706),Color(0xFFB45309),Color(0xFF92400E) )
                        )),
                        shape= RoundedCornerShape(56.sdp)
                    )
                    .padding(4.sdp)
            )
            Text(
                text= APP_TITLE,
                color=colorResource(R.color.black),
                style= TextStyleInter20Lh24Fw600(),
                modifier = Modifier.padding(start=24.sdp)
            )
            Spacer(modifier = Modifier.weight(1f))
            ThemeSelector(
                modifier = Modifier.padding(end = 24.sdp),
            )
        }
        NavGraph(
            modifier= Modifier.weight(1f),
            navController = navController,
            onNavigateToDetail = navigateToDetailsScreen,
            onNavigateToDisplay = navigateToDisplayScreen
        )
        Row(
            modifier = Modifier
                .padding( )
//                .widthIn(max = (LocalConfiguration.current.screenWidthDp ).sdp, min = 300.sdp)
                .fillMaxWidth()
                .height(70.sdp)
//                .clip(RoundedCornerShape(36.sdp))
//                .border(
//                    border = BorderStroke(
//                        width = 2.sdp,
//                        color = colorResource(R.color.white).copy(alpha = 0.2f)
//                    ),
//                    shape = RoundedCornerShape(36.sdp)
//                )
                .background(colorResource(R.color.card_elevated))
                .padding(horizontal = 22.sdp, vertical = 8.sdp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navItems.forEach { (_, itemData) ->
                if (itemData.route != Screen.DisplayScreen.route && itemData.route!= Screen.DetailsScreen.route)
                    NavItem(
                        label = itemData.label,
                        outlinedIconRes = itemData.outlinedIcon,
                        filledIconRes = itemData.filledIcon,
                        isSelected = currentRoute == itemData.route,
                        onClick = {
                            navigateToTab(navController, itemData.route)
                        }
                    )
            }
        }
    }
}