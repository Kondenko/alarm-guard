package com.kondenko.alarmguard.utils

import android.content.Context
import android.media.AudioManager
import android.widget.Toast
import com.kondenko.alarmguard.R
import com.pawegio.kandroid.audioManager

/**
 * Created by vladimir-kondenko on 21.09.16.
 */
object VolumeManager {

    fun increaseVolume(context: Context?, value: Int) {
        context?.audioManager?.setStreamVolume(AudioManager.STREAM_ALARM, value, 0)
        Toast.makeText(context, context?.getString(R.string.toast_volume_increased), Toast.LENGTH_SHORT).show()
    }

}