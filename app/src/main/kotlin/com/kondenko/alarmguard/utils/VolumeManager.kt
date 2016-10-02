package com.kondenko.alarmguard.utils

import android.content.Context
import android.media.AudioManager
import android.widget.Toast
import com.kondenko.alarmguard.R
import com.pawegio.kandroid.audioManager
import com.pawegio.kandroid.i

/**
 * Manages volume changes
 */
object VolumeManager {

    fun increaseVolume(context: Context?, value: Int) {
        i("increaseVolume")
        context?.audioManager?.setStreamVolume(AudioManager.STREAM_ALARM, value, 0)
        Toast.makeText(context, context?.getString(R.string.toast_volume_increased), Toast.LENGTH_SHORT).show()
    }

}