package com.devrachit.groww.utility.composeUtility

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.devrachit.groww.R
import com.devrachit.groww.ui.theme.TextStyleInter12Lh16Fw700


/**
 * A navigation item composable that displays an icon and text
 *
 * @param label The text to display below the icon
 * @param outlinedIconRes Resource ID for the outlined (unselected) version of the icon
 * @param filledIconRes Resource ID for the filled (selected) version of the icon
 * @param isSelected Whether this item is currently selected
 * @param onClick Callback when the item is clicked
 * @param modifier Optional modifier for the composable
 */
@Composable
fun NavItem(
    label: String,
    @DrawableRes outlinedIconRes: Int,
    @DrawableRes filledIconRes: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Animate scale when selected
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.1f else 1.0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "nav_item_scale"
    )

    // Animate color when selected
    val textColor by animateColorAsState(
        targetValue = if (isSelected)
            colorResource(id = R.color.groww_purple)
        else
            colorResource(id = R.color.black).copy(alpha = 0.7f),
        label = "nav_item_text_color"
    )

    Column(
        modifier = modifier
            .clickable(interactionSource = null, indication = null, onClick = onClick)
            .padding()
//            .scale(scale)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        // Display either filled or outlined icon based on selection state

            Box(
                modifier = Modifier
                    .padding(top=0.sdp,bottom = 2.sdp)
                    .fillMaxWidth()
                    .height(4.sdp)
                    .background(color = if (isSelected) colorResource(R.color.groww_purple) else Color.Transparent)
            )
        Icon(
            painter = painterResource(
                id = if (isSelected) filledIconRes else outlinedIconRes
            ),
            contentDescription = label,
            tint = textColor,
            modifier = Modifier
                .padding(top=6.sdp,bottom = 2.sdp)
                .size(24.sdp)
        )

        Text(
            text = label,
            style = TextStyleInter12Lh16Fw700(),
            color = textColor
        )
    }
}