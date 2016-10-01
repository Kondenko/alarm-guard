package com.kondenko.alarmguard.preferences

import android.content.Context
import android.content.res.TypedArray
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceViewHolder
import android.util.AttributeSet
import android.widget.SeekBar
import com.kondenko.alarmguard.Constants
import com.kondenko.alarmguard.R
import com.pawegio.kandroid.audioManager
import com.pawegio.kandroid.onProgressChanged
import java.io.IOException

class MinVolumeSeekBarPreference(context: Context, attrs: AttributeSet) : Preference(context, attrs) {

    private val VOLUME_SCALE_FACTOR = 0.14f

    init {
        layoutResource = R.layout.layout_pref_min_volume
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        super.onBindViewHolder(holder)
        val seekbar = holder?.findViewById(R.id.seekBarMinVolume) as SeekBar
        seekbar.max = context.audioManager?.getStreamMaxVolume(AudioManager.STREAM_ALARM) ?: Constants.DEFAULT_ALARM_VOLUME_MAX
        seekbar.progress = Preferences.defaultVolume
        seekbar.onProgressChanged { value, fromUser -> processProgressChange(value) }
    }

    /**
     * Save selected volume level to Preferences and play an example sound
     *
     * @param volume selected volume level
     */
    private fun processProgressChange(volume: Int) {
        Preferences.defaultVolume = volume
        play(volume.toFloat())
    }

    /**
     * Plays a sound that shows how high the alarm volume will be raised
     *
     * @param volume volume at which to play the sound
     *
     * @see
     * The code is taken from <a href="http://stackoverflow.com/questions/10335057/play-notification-default-sound-only-android">this</a>
     * StackOverflow question
     */
    private fun play(volume: Float) {
        // MediaPlayer requires volume to be a float value between 0.0 and 0.1
        val mediaVolume = volume * VOLUME_SCALE_FACTOR

        val defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val mediaPlayer = MediaPlayer()
        try {
            mediaPlayer.setDataSource(context, defaultRingtoneUri)
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION)
            mediaPlayer.setVolume(mediaVolume, mediaVolume)
            mediaPlayer.prepare()
            mediaPlayer.setOnCompletionListener(MediaPlayer::release)
            mediaPlayer.start()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: SecurityException) {
            e.printStackTrace()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onGetDefaultValue(a: TypedArray, index: Int): Any {
        return a.getInteger(index, Constants.DEFAULT_ALARM_VOLUME)
    }

}