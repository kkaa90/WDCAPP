package com.e.wdcapp.wdcinfo

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import com.e.wdcapp.RouteAction
import java.util.*


@Composable
fun TTTT(routeAction: RouteAction){
    var text by remember {
        mutableStateOf("")
    }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode== Activity.RESULT_OK){
            val r = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            text = r?.get(0).toString()
        }
    }
    
    Column() {
        TextButton(onClick = { routeAction.goBack() }) {
            Text(text = "뒤로가기")
        }
        Text(text = text)
        TextButton(onClick = {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "말하세요")
            launcher.launch(intent)
        }) {
            Text(text = "실행")
        }
    }

}
