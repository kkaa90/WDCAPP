package com.e.wdcapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer(routeAction: RouteAction, drawerState: DrawerState){
    val scope = rememberCoroutineScope()
    val items = listOf(NAVROUTE.MAIN, NAVROUTE.BOARD, NAVROUTE.WDC)
    var selectedItem by remember { mutableStateOf(items[0]) }
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(250.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "로그인", fontSize = 32.sp, modifier = Modifier.clickable { routeAction.navTo(NAVROUTE.LOGIN) })
    }
    items.forEach { item ->
        NavigationDrawerItem(
            label = { Text(item.description) },
            selected = item == selectedItem,
            onClick = {
                selectedItem = item
                scope.launch {
                    routeAction.navTo(item)
                    drawerState.close()
                }
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}