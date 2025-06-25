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
import com.devrachit.groww.ui.theme.GrowwTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupWindow()
        setupSplashScreen()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                setupSplashExitAnimation(splashScreenView)
            }
        }
        setContent {
            GrowwTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(bottom = 32.dp)
                        )
                        ThemeSelector()
                    }
                }
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GrowwTheme {
        Greeting("Android")
    }
}

enum class ThemeMode(val displayName: String, val nightMode: Int) {
    SYSTEM("System Default", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM),
    LIGHT("Light Mode", AppCompatDelegate.MODE_NIGHT_NO),
    DARK("Dark Mode", AppCompatDelegate.MODE_NIGHT_YES)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelector() {
    var expanded by remember { mutableStateOf(false) }
    var selectedTheme by remember { mutableStateOf(ThemeMode.SYSTEM) }

    Column {
        Text(
            text = "Theme Selection",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedTheme.displayName,
                onValueChange = {},
                readOnly = true,
                label = { Text("Choose Theme") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                ThemeMode.values().forEach { theme ->
                    DropdownMenuItem(
                        text = { 
                            Text(
                                text = theme.displayName,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        onClick = {
                            selectedTheme = theme
                            AppCompatDelegate.setDefaultNightMode(theme.nightMode)
                            expanded = false
                        }
                    )
                }
            }
        }
        
        Text(
            text = "Current: ${selectedTheme.displayName}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
