package com.bursarybaby.bursarybaby.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import com.bursarybaby.bursarybaby.R
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay //UI remains responsive while splash is displayed

@Composable  //allows compose to display func
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    //Allows the splash screen to display for 2.5 secs
    LaunchedEffect(Unit) {
        delay(2500)
        onSplashFinished()
    }
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bursary_baby_logo),
            contentDescription = "BursaryBaby Logo",
            modifier = Modifier.size(220.dp),
            contentScale = ContentScale.Fit
        )
    }
}