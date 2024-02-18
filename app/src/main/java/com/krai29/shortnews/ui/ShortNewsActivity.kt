package com.krai29.shortnews.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.krai29.shortnews.ui.navigation.AppNavigationGraph
import com.krai29.shortnews.ui.theme.ShortNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShortNewsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ShortNewsTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    AppEntryPoint()
                }
            }
        }
    }

    @Composable
    fun AppEntryPoint() {
        AppNavigationGraph()
    }
}
