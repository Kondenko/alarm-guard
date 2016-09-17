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

class NotificationActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        i("$intent executed")
        // This is called if the user increases the alarm volume through the notification action
        if (intent?.action.equals(Constants.ACTION_INCREASE_VOLUME)) {
            i("Increasing volume")
            context?.audioManager?.setStreamVolume(AudioManager.STREAM_ALARM, Preferences.prefMinVolume, 0)
            NotificationManagerCompat.from(context).cancel(Constants.NOTIF_LOW_VOLUME_ID)
        }
    }

}
