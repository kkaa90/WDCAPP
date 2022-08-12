package com.e.wdcapp.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.e.wdcapp.MainViewModel
import com.e.wdcapp.NAVROUTE
import com.e.wdcapp.RouteAction
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(routeAction: RouteAction, m: MainViewModel) {
    LaunchedEffect(true) {
        m.lc.init()
    }
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()
    Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            Row() {
                IconButton(onClick = { routeAction.goBack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
            }
        }) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Login",
                style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = "Username") },
                value = m.lc.id,
                onValueChange = { m.lc.id = it })

            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = "Password") },
                value = m.lc.pwd,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { m.lc.pwd = it })
            Spacer(modifier = Modifier.height(20.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        m.lc.idCheck = !m.lc.idCheck
                        if(!m.lc.idCheck) m.lc.autoLogin=false
                    }) {
                    Checkbox(
                        checked = m.lc.idCheck,
                        onCheckedChange = {
                            m.lc.idCheck = !m.lc.idCheck
                            if(!m.lc.idCheck) m.lc.autoLogin=false
                        })
                    Text(text = "ID 저장", modifier = Modifier.padding(4.dp))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        m.lc.autoLogin = !m.lc.autoLogin
                        if(m.lc.autoLogin) m.lc.idCheck=true
                    }) {
                    Checkbox(
                        checked = m.lc.autoLogin,
                        onCheckedChange = {
                            m.lc.autoLogin = !m.lc.autoLogin
                            if(m.lc.autoLogin) m.lc.idCheck=true
                        })
                    Text(text = "자동 로그인", modifier = Modifier.padding(4.dp))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                if (m.lc.id != "" && m.lc.pwd != "") {
                    m.lc.saveInfo()
                    m.lcheck = true
                    routeAction.goBack()
                } else {
                    scope.launch { snackBarHostState.showSnackbar(message = "로그인 오류") }
                }
            }) {
                Text(text = "로그인")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                routeAction.navTo(NAVROUTE.REGISTER)
            }) {
                Text(text = "회원가입")
            }
        }
    }
}