package com.e.wdcapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.e.wdcapp.dataclass.Board
import com.e.wdcapp.dataclass.GameInfo

class MainViewModel : ViewModel() {
    var progress by mutableStateOf(false)

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


}