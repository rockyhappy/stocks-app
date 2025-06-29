package com.devrachit.groww.presentation.screens.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.devrachit.groww.R
import com.devrachit.groww.utility.composeUtility.sdp
import com.valentinilk.shimmer.shimmer

@Composable
fun DetailsScreenShimmer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 22.sdp, end = 22.sdp)
    ) {
        // Company header shimmer
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )
            
            Column(
                modifier = Modifier
                    .padding(start = 12.sdp)
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(20.sdp)
                        .shimmer()
                        .clip(RoundedCornerShape(8.sdp))
                        .background(Color.Gray)
                )
                
                Spacer(modifier = Modifier.height(8.sdp))
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .height(16.sdp)
                        .shimmer()
                        .clip(RoundedCornerShape(8.sdp))
                        .background(Color.Gray)
                )
            }
        }

        // Price info section shimmer
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.sdp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(16.sdp)
                        .shimmer()
                        .clip(RoundedCornerShape(8.sdp))
                        .background(Color.Gray)
                )
                
                Spacer(modifier = Modifier.height(8.sdp))
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(20.sdp)
                        .shimmer()
                        .clip(RoundedCornerShape(8.sdp))
                        .background(Color.Gray)
                )
            }
            
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(16.sdp)
                        .shimmer()
                        .clip(RoundedCornerShape(8.sdp))
                        .background(Color.Gray)
                )
                
                Spacer(modifier = Modifier.height(8.sdp))
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(20.sdp)
                        .shimmer()
                        .clip(RoundedCornerShape(8.sdp))
                        .background(Color.Gray)
                )
            }
        }

        // Chart shimmer
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.sdp)
                .padding(vertical = 16.sdp)
                .shimmer()
                .clip(RoundedCornerShape(12.sdp))
                .background(Color.Gray)
        )

        // About section shimmer
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.sdp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(18.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )
            
            Spacer(modifier = Modifier.height(12.sdp))
            
            repeat(3) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.sdp)
                        .shimmer()
                        .clip(RoundedCornerShape(8.sdp))
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.height(8.sdp))
            }
        }

        // Sector/Industry section shimmer
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.sdp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .height(16.sdp)
                        .shimmer()
                        .clip(RoundedCornerShape(8.sdp))
                        .background(Color.Gray)
                )
                
                Spacer(modifier = Modifier.height(8.sdp))
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(18.sdp)
                        .shimmer()
                        .clip(RoundedCornerShape(8.sdp))
                        .background(Color.Gray)
                )
            }
            
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .height(16.sdp)
                        .shimmer()
                        .clip(RoundedCornerShape(8.sdp))
                        .background(Color.Gray)
                )
                
                Spacer(modifier = Modifier.height(8.sdp))
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(18.sdp)
                        .shimmer()
                        .clip(RoundedCornerShape(8.sdp))
                        .background(Color.Gray)
                )
            }
        }

        Divider(
            color = colorResource(R.color.black).copy(alpha = 0.1f),
            modifier = Modifier.padding(vertical = 10.sdp)
        )

        // Week range shimmer
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.sdp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .height(16.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(8.sdp))
                    .background(Color.Gray)
            )
            
            Spacer(modifier = Modifier.height(12.sdp))
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.sdp)
                    .shimmer()
                    .clip(RoundedCornerShape(4.sdp))
                    .background(Color.Gray)
            )
        }

        Divider(
            color = colorResource(R.color.black).copy(alpha = 0.1f),
            modifier = Modifier.padding(vertical = 10.sdp)
        )

        // Financial metrics shimmer
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.sdp)
        ) {
            repeat(4) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.sdp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .height(14.sdp)
                                .shimmer()
                                .clip(RoundedCornerShape(6.sdp))
                                .background(Color.Gray)
                        )
                        
                        Spacer(modifier = Modifier.height(6.sdp))
                        
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(16.sdp)
                                .shimmer()
                                .clip(RoundedCornerShape(6.sdp))
                                .background(Color.Gray)
                        )
                    }
                    
                    Column(modifier = Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .height(14.sdp)
                                .shimmer()
                                .clip(RoundedCornerShape(6.sdp))
                                .background(Color.Gray)
                        )
                        
                        Spacer(modifier = Modifier.height(6.sdp))
                        
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .height(16.sdp)
                                .shimmer()
                                .clip(RoundedCornerShape(6.sdp))
                                .background(Color.Gray)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DetailsScreenShimmerPreview() {
    DetailsScreenShimmer()
}