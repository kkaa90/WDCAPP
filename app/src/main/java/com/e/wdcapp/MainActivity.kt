package com.e.wdcapp

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.e.wdcapp.board.BoardView
import com.e.wdcapp.board.BoardViewModel
import com.e.wdcapp.dataclass.Board
import com.e.wdcapp.dataclass.GameInfo
import com.e.wdcapp.login.LoginView
import com.e.wdcapp.ui.theme.WDCAPPTheme
import com.e.wdcapp.wdcinfo.TTTT
import com.e.wdcapp.wdcinfo.WDCInfoMain
import com.e.wdcapp.wdcinfo.WdcViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WDCAPPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph()
                }
            }
        }
    }
}

class RouteAction(navHostController: NavHostController) {
    val navTo: (NAVROUTE) -> Unit = { route ->
        navHostController.navigate(route.routeName)
    }

    val navWithNum: (String) -> Unit = { route ->
        navHostController.navigate(route)
    }

    val goBack: () -> Unit = {
        navHostController.popBackStack()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationGraph(starting: String = NAVROUTE.MAIN.routeName) {
    val navController = rememberNavController()
    val routeAction = remember(navController) { RouteAction(navController) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val mainViewModel: MainViewModel = viewModel()
    val boardViewModel: BoardViewModel = viewModel()
    val wdcViewModel: WdcViewModel = viewModel()
    ModalNavigationDrawer(
        drawerContent = {
            Drawer(routeAction = routeAction, drawerState = drawerState)
        },
        drawerState = drawerState,
        gesturesEnabled = !mainViewModel.progress
    ) {
        LaunchedEffect(true) {
            mainViewModel.progress = true
            Handler(Looper.getMainLooper()).postDelayed({ mainViewModel.progress = false }, 2000)
        }
        Box(
            Modifier
                .fillMaxSize()
                .zIndex(1f)
        ) {
            if (mainViewModel.progress) {
                LoadingView()
            }
        }


        NavHost(
            navController = navController,
            startDestination = starting
        ) {
            composable(NAVROUTE.MAIN.routeName) {
                MainView(mainViewModel, routeAction, drawerState, scope)
            }
            composable(NAVROUTE.WDC.routeName) {
                WDCInfoMain(routeAction, drawerState, scope, wdcViewModel)
            }
            composable(NAVROUTE.TEST.routeName) {
                TTTT(routeAction = routeAction)
            }
            composable(NAVROUTE.BOARD.routeName) {
                BoardView(routeAction, scope, drawerState, boardViewModel)
            }
            composable(NAVROUTE.LOGIN.routeName) {
                LoginView(routeAction = routeAction)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(
    mainViewModel: MainViewModel,
    routeAction: RouteAction,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    val scrollState = rememberScrollState()
    var fdVisibility = remember { mutableStateOf(false) }
    BackHandler() {
        fdVisibility.value=true
    }
    Box(modifier = Modifier.fillMaxSize()){
        if(fdVisibility.value){
            FinishDialog(visibility = fdVisibility)
        }
    }
    Scaffold(topBar = {
        TopBar(
            drawerState = drawerState,
            scope = scope,
            routeAction = routeAction
        )
    }) {
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(it)
        ) {
            TabRow(selectedTabIndex = mainViewModel.selectedTab) {
                mainViewModel.list.forEachIndexed { index, item ->
                    Tab(
                        selected = index == mainViewModel.selectedTab,
                        onClick = { mainViewModel.selectedTab = index }) {
                        Text(text = item, modifier = Modifier.padding(8.dp))
                    }
                }
            }
            LazyRow(contentPadding = PaddingValues(16.dp)) {
                items(mainViewModel.gameInformations) { item ->
                    GameView(gameInfo = item)
                }
            }
            TextButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "경기 더보기")
            }
            Divider()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                Text(text = "나의 관심팀", fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")
                }
            }
            Text(text = "대한민국", fontSize = 16.sp, modifier = Modifier.align(CenterHorizontally))
            FavoriteTeamGame(gameInfo = mainViewModel.fgi[0])
            FavoriteTeamGame(gameInfo = mainViewModel.fgi[1])
            TextButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "경기 더보기")
            }
            Divider()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                Text(text = "게시물", fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")
                }
            }
            BoardMain(board = mainViewModel.tempBoards[0])
            BoardMain(board = mainViewModel.tempBoards[1])
            BoardMain(board = mainViewModel.tempBoards[2])

        }
    }
}

@Composable
fun GameView(gameInfo: GameInfo) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ball),
            contentDescription = "",
            modifier = Modifier
                .size(100.dp)
                .padding(8.dp)
        )
        Text(text = gameInfo.teams, modifier = Modifier.padding(4.dp))
        Text(text = gameInfo.times, modifier = Modifier.padding(4.dp))
    }
}

@Composable
fun FavoriteTeamGame(gameInfo: GameInfo) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.ball),
            contentDescription = "",
            modifier = Modifier.size(60.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(text = gameInfo.teams)
            Text(text = gameInfo.times, fontSize = 12.sp, color = Color.Gray)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "1팀 응원")
                }
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "2팀 응원")
                }
            }
        }
    }
}

@Composable
fun BoardMain(board: Board) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .border(1.dp, Color.Black),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(text = board.title)
            Text(text = board.time, fontSize = 12.sp)
            Text(text = board.nick, fontSize = 12.sp)
        }
        Box(
            contentAlignment = Center, modifier = Modifier
                .padding(end = 8.dp)
                .size(40.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.LightGray)
        ) {
            Text(text = board.comment.toString())
        }


    }
}

@Composable
fun FinishDialog(visibility: MutableState<Boolean>) {
    val context = LocalContext.current as Activity
    AlertDialog(onDismissRequest = { visibility.value = false }, 
        confirmButton = {
        TextButton(onClick = { context.finish() }) {
            Text(text = "확인")
        }},
        dismissButton = {
            TextButton(onClick = { visibility.value = false }) {
                Text(text = "취소")
            }
        },
        title = { Text(text = "앱 종료")},
        text = { Text(text = "종료하시겠습니까?")}
    ) 
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    WDCAPPTheme {


    }
}