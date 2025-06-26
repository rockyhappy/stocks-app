package com.devrachit.groww.ui.theme

import androidx.appcompat.app.AppCompatDelegate

enum class ThemeMode(val displayName: String, val nightMode: Int) {
    SYSTEM("System Default", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM),
    LIGHT("Light Mode", AppCompatDelegate.MODE_NIGHT_NO),
    DARK("Dark Mode", AppCompatDelegate.MODE_NIGHT_YES)
}