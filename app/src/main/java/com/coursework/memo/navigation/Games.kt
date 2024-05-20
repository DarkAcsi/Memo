package com.coursework.memo.navigation

sealed class Games(val kind: String) {
    data object Classic: Games("classicGame")
    data object Find: Games("findGame")
    data object House: Games("houseGame")
}