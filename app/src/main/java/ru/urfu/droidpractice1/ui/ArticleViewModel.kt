package ru.urfu.droidpractice1.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ArticleViewModel : ViewModel() {
    var likes by mutableIntStateOf(0)
    var isRead by mutableStateOf(false)
}