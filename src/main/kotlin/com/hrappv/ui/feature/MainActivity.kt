package com.hrappv.ui.feature

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.rememberWindowState
//import androidx.compose.ui.platform.LocalConfiguration
import com.arkivanov.decompose.extensions.compose.jetbrains.rememberRootComponent
import com.hrappv.App
import com.hrappv.ui.navigation.NavHostComponent
import com.hrappv.ui.value.HrAppVTheme
import com.theapache64.cyclone.core.Activity
import com.theapache64.cyclone.core.Intent
import androidx.compose.ui.window.application as setContent

/**
 * The activity who will be hosting all screens in this app
 */
class MainActivity : Activity() {
    companion object {
        fun getStartIntent(): Intent {
            return Intent(MainActivity::class).apply {
                // data goes here
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

//        val configuration = LocalConfiguration.current
//        val screenHeight = configuration.screenHeightDp.dp
//        val screenWidth = configuration.screenWidthDp.dp

        setContent {
            Window(
                onCloseRequest = ::exitApplication,
                title = "${App.appArgs.appName} (${App.appArgs.version})",
                icon = painterResource("drawables/launcher_icons/system.png"),
                state = rememberWindowState(placement = WindowPlacement.Maximized,
                    width = 1024.dp, height = 600.dp),
//                state = rememberWindowState(width = screenWidth, height = screenHeight),
            ) {
                HrAppVTheme(true) {
                    // Igniting navigation
                    rememberRootComponent(factory = ::NavHostComponent)
                        .render()
                }
            }

        }

    }
}