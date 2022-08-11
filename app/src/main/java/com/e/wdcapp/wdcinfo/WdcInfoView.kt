package com.e.wdcapp.wdcinfo

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.e.wdcapp.RouteAction
import com.e.wdcapp.TopBar

import com.e.wdcapp.ui.theme.WDCAPPTheme
import kotlinx.coroutines.CoroutineScope
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WDCInfoMain(routeAction: RouteAction, drawerState: DrawerState, scope: CoroutineScope) {
    Scaffold(topBar = { TopBar(drawerState, scope, routeAction)}) {
        Column(modifier = Modifier.fillMaxSize().padding(it)) {
            Text("경기 일정", modifier = Modifier.padding(8.dp))
            InfoBox()
            Text("현재 순위", modifier = Modifier.padding(8.dp))
            RankInfo()
        }
    }

}


@Composable
fun InfoBox() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black)
    ) {
        DateInfo()
        VersusInfo()
    }
}

@Composable
fun DateInfo() {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "")
        }
        Text(text = "00/00(일)")
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = "")
        }
    }
}

@Composable
fun VersusInfo() {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(text = "VS")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = "대한민국")
                Text(text = "대한민국")
            }
        }
        Text(text = "00:00", Modifier.padding(4.dp))
    }
}

@Composable
fun RankInfo() {
    val rankList = listOf("A조", "B조", "C조", "D조", "E조", "F조", "G조", "H조")
    var rankNow by remember { mutableStateOf(0) }
    val hScroll = rememberScrollState()
    Column(Modifier.fillMaxWidth()) {
        ScrollableTabRow(
            selectedTabIndex = rankNow,
            edgePadding = 0.dp,
            contentColor = Color.White,
            containerColor = Color.Black
        ) {
            rankList.forEachIndexed { index, s ->
                Tab(selected = index == rankNow, onClick = { rankNow = index }) {
                    Text(text = s, color = Color.White)
                }
            }
        }
        Row(modifier = Modifier.horizontalScroll(hScroll)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(text = "순위")
                Text(text = "1")
                Text(text = "2")
                Text(text = "3")
                Text(text = "4")
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(text = "팀")
                Text(text = "${rankList[rankNow]}팀111111111111111")
                Text(text = "${rankList[rankNow]}팀222222222222222")
                Text(text = "${rankList[rankNow]}팀333333333333333")
                Text(text = "${rankList[rankNow]}팀444444444444444")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(text = "경기수")
                Text(text = "0")
                Text(text = "0")
                Text(text = "0")
                Text(text = "0")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(text = "승점")
                Text(text = "0")
                Text(text = "0")
                Text(text = "0")
                Text(text = "0")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(text = "승")
                Text(text = "0")
                Text(text = "0")
                Text(text = "0")
                Text(text = "0")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(text = "무")
                Text(text = "0")
                Text(text = "0")
                Text(text = "0")
                Text(text = "0")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(text = "패")
                Text(text = "0")
                Text(text = "0")
                Text(text = "0")
                Text(text = "0")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(text = "득점")
                Text(text = "0")
                Text(text = "0")
                Text(text = "0")
                Text(text = "0")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(text = "실점")
                Text(text = "0")
                Text(text = "0")
                Text(text = "0")
                Text(text = "0")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(text = "득실차")
                Text(text = "0")
                Text(text = "0")
                Text(text = "0")
                Text(text = "0")
            }
        }

    }
}


@SuppressLint("SimpleDateFormat")
fun toSimpleString(date: Date): String {
    val format = SimpleDateFormat("MM/yy/dd")
    return format.format(date)
}
