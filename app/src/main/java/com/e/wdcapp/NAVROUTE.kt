package com.e.wdcapp

enum class NAVROUTE(val routeName : String, val description : String) {
    LOADING("loading","로딩"),
    MAIN("main","메인"),
    LOGIN("login","로그인"),
    REGISTER("register","회원가입"),
    PROFILE("profile", "회원정보"),
    BOARD("board","게시판"),
    BOARDDETAIL("boardDetail","게시판 상세"),
    WRITING("writingBoard","게시판 쓰기"),
    SEARCH("search","검색"),
    NOTIFICATION("notification","알림"),
    WDC("wdc","월드컵 정보"),
    WDCDETAIL("wdcDetail","월드컵 상세"),
    TEST("test","테스트용")
}