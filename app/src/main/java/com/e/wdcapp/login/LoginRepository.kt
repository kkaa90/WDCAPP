package com.e.wdcapp.login

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.e.wdcapp.LoginInfo
import com.e.wdcapp.dataclass.LoginData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.loginDataStore : DataStore<LoginInfo> by dataStore(
    fileName = "loginInfo.pb",
    serializer = LoginSetting
)

class LoginRepository(context: Context) {
    private val loginDataStore = context.loginDataStore

    suspend fun readLoginInfo() : LoginInfo {
        return loginDataStore.data.map { it }.first()
    }
    suspend fun writeLoginInfo(loginData: LoginData) {
        loginDataStore.updateData { login ->
            login.toBuilder()
                .setId(loginData.id)
                .setPwd(loginData.pwd)
                .setSaveId(loginData.idSave)
                .setAutoLogin(loginData.autoLogin)
                .build()
        }
    }
}