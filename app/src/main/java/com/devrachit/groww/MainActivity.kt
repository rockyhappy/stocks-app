package com.devrachit.groww

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.devrachit.groww.data.local.datastore.DataStoreRepository
import com.devrachit.groww.presentation.navigation.MainNavGraph
import com.devrachit.groww.ui.theme.GrowwTheme
import com.devrachit.groww.ui.theme.ThemeMode
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    @Inject
    lateinit var dataStoreRepository: DataStoreRepository
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeTheme()
        setupWindow()
        setupSplashScreen()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                setupSplashExitAnimation(splashScreenView)
            }
        }
        setContent {
            GrowwTheme {
                val navController = rememberNavController()
                MainNavGraph(navController = navController)
            }
        }
    }

    private fun initializeTheme() {
        lifecycleScope.launch {
            try {
                val savedTheme = dataStoreRepository.readThemeMode()
                val themeMode = when (savedTheme) {
                    "LIGHT" -> ThemeMode.LIGHT
                    "DARK" -> ThemeMode.DARK
                    "SYSTEM" -> ThemeMode.SYSTEM
                    else -> ThemeMode.SYSTEM
                }
                AppCompatDelegate.setDefaultNightMode(themeMode.nightMode)
            } catch (e: Exception) {
                // Fallback to system default if there's an error
                AppCompatDelegate.setDefaultNightMode(ThemeMode.SYSTEM.nightMode)
            }
        }
    }

    private fun setupWindow() {
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun setupSplashScreen() {
        installSplashScreen()
    }

    private fun setupSplashExitAnimation(splashScreenView: View) {
        ObjectAnimator.ofFloat(
            splashScreenView,
            View.TRANSLATION_X,
            0f,
            -splashScreenView.height.toFloat()
        ).apply {
            duration = 600
            doOnEnd {
                (splashScreenView.parent as? ViewGroup)?.removeView(splashScreenView)
            }
        }.also {
            it.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}

