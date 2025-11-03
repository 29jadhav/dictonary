package com.vivek.dictionaryapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vivek.core.util.Result
import com.vivek.dictionaryapp.AudioPlayer
import com.vivek.domain.repository.DictionaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dictionaryRepository: DictionaryRepository,
    private val audioPlayer: AudioPlayer
) :
    ViewModel() {
    private val _mainState = MutableStateFlow(MainState())
    val mainState = _mainState.asStateFlow()

    private val searchJob: Job? = null

    fun onEvent(onUIEvent: MainUIEvent) {
        when (onUIEvent) {
            MainUIEvent.OnSearchClick -> {
                loadWord()
            }

            is MainUIEvent.OnSearchWordChange -> {
                _mainState.update { mainState ->
                    mainState.copy(searchWord = onUIEvent.word)
                }
            }

            is MainUIEvent.OnSpeakerClick -> {
                viewModelScope.launch {
                    val audioUrl = _mainState.value.wordItem?.phonetics
                        ?.find { it.audio?.isNotBlank() ?: false }?.audio

                    audioUrl?.let {
                        audioPlayer.playAudio(it)
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        audioPlayer.release() // Release player when ViewModel is cleared
    }

    private fun loadWord() {
        viewModelScope.launch {
            dictionaryRepository.getWordResult(_mainState.value.searchWord)
                .collect(collector = { result ->
                    run {
                        when (result) {
                            is Result.Error -> {
                                Log.d("TAG","error = ${result.message}")
                                _mainState.update {
                                    mainState ->
                                    mainState.copy(errorMessage = result.message, wordItem = null)
                                }
                            }
                            is Result.Loading -> {
                                _mainState.update { mainState ->
                                    mainState.copy(isLoading = result.isLoading)
                                }
                            }

                            is Result.Success -> {
                                result.data?.let { wordItem ->
                                    _mainState.update { mainState ->
                                        mainState.copy(wordItem = wordItem)
                                    }
                                }
                            }
                        }
                    }
                }
                )
        }
    }
}