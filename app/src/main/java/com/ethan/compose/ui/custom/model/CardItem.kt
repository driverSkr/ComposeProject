package com.ethan.compose.ui.custom.model

data class CardItem(
    val name: String,
    val isNeedEndIcon: Boolean,
    val isCompleted: Boolean = true,
    val onClick: () -> Unit = {}
)
