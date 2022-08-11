package com.e.wdcapp.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.e.wdcapp.MainViewModel
import com.e.wdcapp.RouteAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterView(routeAction: RouteAction, m: MainViewModel) {
    LaunchedEffect(true){
        m.rc.init()
    }
    Scaffold(topBar = {
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Register",
                style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = "id") },
                value = m.rc.id,
                onValueChange = { m.rc.id = it })
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = "pwd") },
                value = m.rc.pwd,
                onValueChange = { m.rc.pwd = it })
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = "pwdCheck") },
                value = m.rc.pwd2,
                onValueChange = { m.rc.pwd2 = it })
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = "email") },
                value = m.rc.email,
                onValueChange = { m.rc.email = it })
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = "nickname") },
                value = m.rc.nick,
                onValueChange = { m.rc.nick = it })
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Button(onClick = {
                    m.rc.init()
                }) {
                    Text(text = "초기화")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(onClick = {

                }) {
                    Text(text = "회원가입")
                }
            }
        }
    }
}