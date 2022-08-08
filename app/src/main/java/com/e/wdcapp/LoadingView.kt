package com.e.wdcapp

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LoadingView(routeAction: RouteAction, mainViewModel: MainViewModel) {
    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LaunchedEffect(true){
                Handler(Looper.getMainLooper()).postDelayed({routeAction.navTo(NAVROUTE.MAIN);mainViewModel.progress=true},2000)

            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.ball), contentDescription = "", modifier = Modifier.size(200.dp))
                Text(text = "World Cup Info")
            }
        }
    }
}