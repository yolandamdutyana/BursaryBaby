package com.bursarybaby.bursarybaby

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bursarybaby.bursarybaby.ui.auth.LoginScreen
import com.bursarybaby.bursarybaby.ui.theme.BursaryBabyTheme
import com.bursarybaby.bursarybaby.ui.splash.SplashScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BursaryBabyTheme { //Material 3 theme..design & style guide
                SplashScreen {  }
                LoginScreen()
            }
        }
    }
}


