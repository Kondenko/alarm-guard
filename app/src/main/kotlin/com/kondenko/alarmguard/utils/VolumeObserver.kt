package com.kondenko.alarmguard.utils

import android.app.Activity
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.database.ContentObserver
import android.media.AudioManager
import android.os.Handler
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.widget.Toast
import com.kondenko.alarmguard.Constants
import com.kondenko.alarmguard.R
import com.kondenko.alarmguard.preferences.Preferences
import com.pawegio.kandroid.i

/**
 * Sends a notification whenever alarm volume changes.
 */
class VolumeObserver(val context: Context, handler: Handler?) : ContentObserver(handler) {

    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val volume = audioManager.getStreamVolume(AudioManager.STREAM_ALARM)
        if (volume < com.kondenko.alarmguard.preferences.Preferences.defaultVolume) {
            if (Preferences.increaseAutomatically) {
                VolumeManager.increaseVolume(context, Preferences.defaultVolume)
            } else {
                sendNotificationLowVolume()
            }
        } else {
            NotificationManagerCompat.from(context).cancel(Constants.NOTIF_LOW_VOLUME_ID)
        }
    }

    /**
     * Sends a notification which tell the user his alarm volume is low
     * and suggests to increase it
     */
    private fun sendNotificationLowVolume() {
        // Creating a BroadcastReceiver which contains the method to invoke
        // when the user clicks the action button in this notification
        val actionIntent = Intent(context, NotificationActionReceiver::class.java).setAction(Constants.ACTION_INCREASE_VOLUME)

        val actionPendingIntent = PendingIntent.getBroadcast(
                context,
                1,
                actionIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or Notification.FLAG_AUTO_CANCEL
        )

        val action = NotificationCompat.Action(R.drawable.ic_volume_up_black_24dp,
                context.getString(R.string.notif_action_increase_volume),
                actionPendingIntent)

        val notification = with(NotificationCompat.Builder(context)) {
            setSmallIcon(R.drawable.ic_alarm_off_white_24dp)
            setContentTitle(context.getString(R.string.notif_title_volume_off))
            addAction(action)
            build()
        }

        NotificationManagerCompat.from(context).notify(Constants.NOTIF_LOW_VOLUME_ID, notification)
    }

}
