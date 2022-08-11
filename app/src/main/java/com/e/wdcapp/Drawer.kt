package com.e.wdcapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer(routeAction: RouteAction, drawerState: DrawerState, m: MainViewModel) {
    val scope = rememberCoroutineScope()
    val items = listOf(NAVROUTE.MAIN, NAVROUTE.BOARD, NAVROUTE.WDC)
    var selectedItem by remember { mutableStateOf(items[0]) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (m.lcheck) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ball),
                    contentDescription = "",
                    modifier = Modifier.size(50.dp)
                )
                Text(text = "닉네임 : 대대대대")
                Text(text = "선호팀 : 대한민국")
                Row() {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "내 정보")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    TextButton(onClick = { m.lcheck = false }) {
                        Text(text = "로그아웃")
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .clickable { scope.launch { routeAction.navTo(NAVROUTE.LOGIN); drawerState.close() } },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "로그인",
                    fontSize = 32.sp
                )
            }
        }

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