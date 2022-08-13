package com.e.wdcapp.board

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.e.wdcapp.MainViewModel
import com.e.wdcapp.RouteAction
import com.e.wdcapp.TopBar
import com.e.wdcapp.dataclass.Board
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardView(routeAction: RouteAction, scope: CoroutineScope, drawerState: DrawerState, m: MainViewModel){
    Scaffold(topBar = { TopBar(drawerState, scope, routeAction) }) { paddingValue ->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValue)){
            items(m.tempBoards){ item ->
                BoardItem(board = item)
            }
        }
    }
}

@Composable
fun BoardItem(board: Board){
    Row(modifier = Modifier.fillMaxWidth()) {
        Column() {

        }
    }
}