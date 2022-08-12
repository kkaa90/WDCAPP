package com.e.wdcapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.e.wdcapp.dataclass.Board
import com.e.wdcapp.dataclass.GameInfo
import com.e.wdcapp.dataclass.LoginData
import com.e.wdcapp.login.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class MainViewModel : ViewModel() {

    var progress by mutableStateOf(false)
    var lCheck by mutableStateOf(false)
    fun readLoginInfo() : LoginInfo {
        val context = App.context()
        val repository = LoginRepository(context)
        var loginInfo : LoginInfo
        runBlocking(Dispatchers.IO) {
            loginInfo = repository.readLoginInfo()
        }
        return loginInfo
    }

    fun writeLoginInfo(loginData: LoginData?){
        val context = App.context()
        val repository = LoginRepository(context)
        runBlocking (Dispatchers.IO){
            if(loginData!=null){
                repository.writeLoginInfo(loginData)
            }
        }
        return
    }
    var rc = RegisterClass()
    var lc = LoginClass()




    val list = listOf("오늘 경기", "어제 경기", "내일 경기")
    var selectedTab by mutableStateOf(0)
    val gameInformations = listOf(
        GameInfo("중국 vs 한국", "11:00"),
        GameInfo("미국 vs 일본", "13:00"),
        GameInfo("이란 vs 이라크", "15:00"),
        GameInfo("중국 vs 한국", "11:00"),
        GameInfo("미국 vs 일본", "13:00"),
        GameInfo("이란 vs 이라크", "15:00")
    )
    val fgi = listOf(
        GameInfo("중국 vs 한국", "2022/11/11 11:00"),
        GameInfo("미국 vs 한국", "2022/11/13 11:00"),
    )

    val tempBoards = listOf(
        Board("대한민국 화이팅1","2022/11/10 11:00","민기형님",5),
        Board("대한민국 화이팅2","2022/11/10 11:00","민기형님",5),
        Board("대한민국 화이팅3","2022/11/10 11:00","민기형님",5)
    )

    class RegisterClass(){
        var id by mutableStateOf("")
        var pwd by mutableStateOf("")
        var pwd2 by mutableStateOf("")
        var email by mutableStateOf("")
        var nick by mutableStateOf("")
        fun init() {
            id = ""
            pwd = ""
            pwd2 = ""
            email = ""
            nick = ""
        }
    }
    class LoginClass(){
        var id by mutableStateOf("")
        var pwd by mutableStateOf("")
        var idCheck by mutableStateOf(false)
        var autoLogin by mutableStateOf(false)
        fun init(){
            val l = readLoginInfo()
            id = l.id
            pwd = l.pwd
            idCheck = l.saveId
            autoLogin = l.autoLogin
        }
        fun saveInfo(){
            if(autoLogin){
                writeLoginInfo(LoginData(id,pwd,idCheck,autoLogin))
            }
            else if(idCheck){
                writeLoginInfo(LoginData(id,"",idCheck,autoLogin))
            }
            else {
                writeLoginInfo(LoginData("","",idCheck,autoLogin))
            }
        }
        fun autoCheck() : Boolean{
            val l = readLoginInfo()
            return l.autoLogin 
        }
        private fun readLoginInfo() : LoginInfo {
            val context = App.context()
            val repository = LoginRepository(context)
            var loginInfo : LoginInfo
            runBlocking(Dispatchers.IO) {
                loginInfo = repository.readLoginInfo()
            }
            return loginInfo
        }
        private fun writeLoginInfo(loginData: LoginData?){
            val context = App.context()
            val repository = LoginRepository(context)
            runBlocking (Dispatchers.IO){
                if(loginData!=null){
                    repository.writeLoginInfo(loginData)
                }
            }
            return
        }
    }
}