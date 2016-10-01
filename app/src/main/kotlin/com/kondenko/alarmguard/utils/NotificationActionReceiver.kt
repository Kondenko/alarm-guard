package com.kondenko.alarmguard.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.support.v4.app.NotificationManagerCompat
import com.kondenko.alarmguard.Constants
import com.kondenko.alarmguard.preferences.Preferences
import com.pawegio.kandroid.audioManager
import com.pawegio.kandroid.i

/**
 * Manages app notification action(s)
 */
class NotificationActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        // This is called if the user increases the alarm volume through the notification action
        if (intent?.action.equals(Constants.ACTION_INCREASE_VOLUME)) {
           VolumeManager.increaseVolume(context, Preferences.defaultVolume)
        }
    }


}
