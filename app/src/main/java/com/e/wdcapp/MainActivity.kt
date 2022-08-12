package com.e.wdcapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.e.wdcapp.board.BoardView
import com.e.wdcapp.login.LoginView
import com.e.wdcapp.login.RegisterView
import com.e.wdcapp.ui.theme.WDCAPPTheme
import com.e.wdcapp.wdcinfo.TTTT
import com.e.wdcapp.wdcinfo.WDCInfoMain

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
fun NavigationGraph(starting: String = NAVROUTE.LOADING.routeName) {
    val navController = rememberNavController()
    val routeAction = remember(navController) { RouteAction(navController) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val mainViewModel: MainViewModel = viewModel()
    ModalNavigationDrawer(
        drawerContent = {
            Drawer(routeAction = routeAction, drawerState = drawerState, mainViewModel)
        },
        drawerState = drawerState,
        gesturesEnabled = mainViewModel.progress
    ) {
        NavHost(
            navController = navController,
            startDestination = starting
        ) {
            composable(NAVROUTE.LOADING.routeName){
                LoadingView(routeAction = routeAction, mainViewModel)
            }
            composable(NAVROUTE.MAIN.routeName) {
                MainView(mainViewModel, routeAction, drawerState, scope)
            }
            composable(NAVROUTE.WDC.routeName) {
                WDCInfoMain(routeAction, drawerState, scope, )
            }
            composable(NAVROUTE.TEST.routeName) {
                TTTT(routeAction = routeAction)
            }
            composable(NAVROUTE.BOARD.routeName) {
                BoardView(routeAction, scope, drawerState, mainViewModel)
            }
            composable(NAVROUTE.LOGIN.routeName) {
                LoginView(routeAction = routeAction,m = mainViewModel)
            }
            composable(NAVROUTE.REGISTER.routeName){
                RegisterView(routeAction = routeAction, m = mainViewModel)
            }
        }
    }

}



@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    WDCAPPTheme {


    }
}