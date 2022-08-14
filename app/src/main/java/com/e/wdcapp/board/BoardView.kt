package com.e.wdcapp.board

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.e.wdcapp.MainViewModel
import com.e.wdcapp.RouteAction
import com.e.wdcapp.TopBar
import com.e.wdcapp.dataclass.Board
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardView(
    routeAction: RouteAction,
    scope: CoroutineScope,
    drawerState: DrawerState,
    m: MainViewModel
) {
    var searchKeyword by remember {
        mutableStateOf("")
    }
    val list = listOf("제목", "제목+내용", "글쓴이")
    var searchType by remember {
        mutableStateOf(list[0])
    }
    var visibility by remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current
    Scaffold(topBar = { TopBar(drawerState, scope, routeAction) }) { paddingValue ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            items(m.tempBoards) { item ->
                BoardItem(board = item)
            }
            item {

                Row(
                    modifier = Modifier
                        .border(1.dp, color = Color.Black)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Column(modifier = Modifier.height(60.dp).width(80.dp).clickable { visibility = !visibility },
                        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text(text = searchType, fontSize = 12.sp )
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
                        }
                        DropdownMenu(
                            expanded = visibility,
                            onDismissRequest = { visibility = false }) {
                            list.forEach { l ->
                                DropdownMenuItem(
                                    text = { Text(text = l) },
                                    onClick = { searchType = l;visibility=false })
                            }
                        }
                    }
                    TextField(
                        value = searchKeyword,
                        onValueChange = { searchKeyword = it },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {focusManager.clearFocus()}
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp, end = 4.dp),
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            containerColor = Color.LightGray
                        )
                    )
                    Button(onClick = { /*TODO*/ }, ) {
                        Text(text = "검색")
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }
    }
}

@Composable
fun BoardItem(board: Board) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column() {
            Text(text = board.title, modifier = Modifier.padding(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "${board.nick} 조회 0 추천 0", fontSize = 12.sp, color = Color.Gray)
                Text(text = board.time, fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}