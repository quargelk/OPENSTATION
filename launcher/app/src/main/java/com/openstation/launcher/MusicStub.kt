package com.openstation.launcher

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import org.json.JSONObject

/** Яндекс Музыка удалена из лаунчера. Лёгкие заглушки, чтобы остальной код
 *  (home now-playing, аудио-сервис) компилировался. Музыка — через Spotify-приложение. */
data class YmAccount(
    val loggedIn: Boolean = false,
    val login: String = "",
    val fullName: String = "",
    val hasPlus: Boolean = false
)

data class YmTrack(
    val title: String = "",
    val artists: String = "",
    val album: String = "",
    val durationMs: Long = 0L,
    val liked: Boolean = false
) {
    fun isNotEmpty(): Boolean = title.isNotEmpty() || artists.isNotEmpty()
    fun coverUrl(@Suppress("UNUSED_PARAMETER") size: Int = 0): String = ""
}

data class YmPlayerSnapshot(
    val seq: Long = 0L,
    val playing: Boolean = false,
    val buffering: Boolean = false,
    val currentTrack: YmTrack? = null,
    val durationMs: Long = 0L,
    val positionMs: Long = 0L,
    val account: YmAccount = YmAccount(),
    val sourceLabel: String = "",
    val lastError: String = ""
) {
    companion object { fun fromJson(@Suppress("UNUSED_PARAMETER") j: JSONObject): YmPlayerSnapshot = YmPlayerSnapshot() }
}

class YandexMusicApi {
    fun loadCoverBitmap(@Suppress("UNUSED_PARAMETER") url: String, @Suppress("UNUSED_PARAMETER") size: Int): android.graphics.Bitmap? = null
}

class YandexMusicPlayer(@Suppress("UNUSED_PARAMETER") ctx: Context) {
    companion object {
        const val EXTRA_SNAPSHOT_JSON = "snapshot_json"
        const val ACTION_MUSIC_STATE = "com.openstation.launcher.MUSIC_STATE"
    }
    val api: YandexMusicApi? = null
    fun getSnapshot(): YmPlayerSnapshot = YmPlayerSnapshot()
    fun emitSnapshot(forceDaemon: Boolean = false) {}
    fun dislikeCurrent() {}
    fun playLikedTracks(openUiOnAuthNeeded: Boolean = false) {}
    fun playChartTracks(openUiOnAuthNeeded: Boolean = false) {}
    fun playMyWave(mood: String, seed: String = "", openUiOnAuthNeeded: Boolean = false) {}
    fun playQuery(query: String, openUiOnAuthNeeded: Boolean = false) {}
    fun setOAuthToken(token: String, autoResumeMyWave: Boolean = false) {}
    fun setDuck(duck: Boolean) {}
    fun release() {}
    fun togglePlayPause() {}
    fun play() {}
    fun pause() {}
    fun stop() {}
    fun next() {}
    fun prev() {}
    fun toggleLikeCurrent() {}
}

/** Заглушка экрана: Я.Музыка удалена — просто закрывается. */
class YandexMusicActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Яндекс Музыка удалена — используйте Spotify", Toast.LENGTH_SHORT).show()
        finish()
    }
}
