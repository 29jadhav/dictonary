package com.vivek.dictionaryapp.presentation

sealed class MainUIEvent {
    data class OnSearchWordChange(val word: String): MainUIEvent()
    data object OnSearchClick : MainUIEvent()

    object OnSpeakerClick : MainUIEvent()
}