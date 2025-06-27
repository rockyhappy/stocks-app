package com.devrachit.groww.presentation.screens.details.components


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devrachit.groww.R
import com.devrachit.groww.utility.composeUtility.sdp


@Composable
fun ExpandableContainer(
    heading: String,
    description: String
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.white), RoundedCornerShape(8.dp))
            .animateContentSize()
            .clickable { expanded = !expanded }
            ,
        elevation = CardDefaults.cardElevation(1.sdp)

    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = heading,
//                    fontFamily = Font(R.font.inter_medium_500),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    color= colorResource(R.color.black),
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown ,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    modifier = if(expanded)Modifier.height(24.dp).width(24.dp).rotate(180f) else Modifier.height(24.dp).width(24.dp),
                    tint =colorResource(R.color.black),
                )
            }
            if (expanded) {
                Text(
                    text = description,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,

//                    fontFamily = Constants.CustomFontFamily,
                    color= colorResource(R.color.black),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start=16.dp,end = 16.dp,bottom = 16.sdp,top = 8.dp)
                )
            }
        }
    }
}