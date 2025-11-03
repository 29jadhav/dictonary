package com.vivek.dictionaryapp

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AudioPlayer @Inject constructor(
    @ApplicationContext context: Context
) {
    private var player: ExoPlayer? = ExoPlayer.Builder(context).build()

    fun playAudio(url: String) {
        if (url.isBlank()) return

        val mediaItem = MediaItem.fromUri(url)
        player?.apply {
            setMediaItem(mediaItem)
            setHandleAudioBecomingNoisy(true)
            prepare()
            play()
        }
    }

    fun release() {
        player?.release()
        player = null
    }
}