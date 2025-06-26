package com.devrachit.groww.utility.composeUtility

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.devrachit.groww.R
import com.devrachit.groww.ui.theme.ThemeMode


@Composable
fun ThemeSelector(
    modifier: Modifier = Modifier,
    currentTheme: ThemeMode = ThemeMode.SYSTEM,
    onThemeSelected: (ThemeMode) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }

    fun getThemeIcon(theme: ThemeMode): Int {
        return when (theme) {
            ThemeMode.LIGHT -> R.drawable.ic_light_mode
            ThemeMode.DARK -> R.drawable.ic_dark_mode
            ThemeMode.SYSTEM -> R.drawable.ic_system_default
        }
    }

    Column(
        modifier = modifier
    )
    {
        Box {
            Icon(
                painter = painterResource(getThemeIcon(currentTheme)),
                contentDescription = "Theme",
                tint = colorResource(R.color.black),
                modifier = Modifier
                    .size(32.dp)
                    .clickable { expanded = true }
                    .padding(4.dp)
            )

            // Dropdown menu
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                offset = DpOffset(x = (-16).dp, y = 4.dp),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorResource(R.color.white))
                    .border(
                        width = 1.dp,
                        color = colorResource(R.color.black).copy(alpha = 0.3f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                ThemeMode.values().forEachIndexed { index, theme ->
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(
                                painter = painterResource(getThemeIcon(theme)),
                                tint = colorResource(R.color.black),
                                contentDescription = null,
                                modifier = Modifier.size(22.dp)
                            )
                        },
                        text = { Text(text = theme.displayName, color = colorResource(R.color.black)) },
                        onClick = {
                            onThemeSelected(theme)
                            expanded = false
                        }
                    )
                    if (index < ThemeMode.values().size - 1) {
                        HorizontalDivider(
                            color = colorResource(R.color.white).copy(alpha = 0.1f)
                        )
                    }
                }
            }
        }
    }
}
