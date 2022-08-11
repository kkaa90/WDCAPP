package com.e.wdcapp.board

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.e.wdcapp.Drawer
import com.e.wdcapp.RouteAction
import com.e.wdcapp.TopBar
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardView(routeAction: RouteAction, scope: CoroutineScope, drawerState: DrawerState){
    Scaffold(topBar = { TopBar(drawerState, scope, routeAction) }) {

    }
}