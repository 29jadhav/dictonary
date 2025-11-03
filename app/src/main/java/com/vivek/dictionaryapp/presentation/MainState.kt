package com.vivek.dictionaryapp.presentation

import com.vivek.domain.model.WordItem

data class MainState(
    val isLoading: Boolean = false,
    val searchWord: String = "",
    val wordItem: WordItem? = null,
    val errorMessage: String? = null // Add this line
)