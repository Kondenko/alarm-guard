package com.kondenko.alarmguard.utils

import android.app.IntentService
import android.content.Intent
import android.os.Handler

class VolumeCheckerService() : IntentService("VolumeCheckerService") {

    private lateinit var volumeObserver: VolumeObserver

    override fun onCreate() {
        super.onCreate()
        volumeObserver = VolumeObserver(this, Handler())
        contentResolver.registerContentObserver(android.provider.Settings.System.CONTENT_URI, true, volumeObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        contentResolver.unregisterContentObserver(volumeObserver)
    }

    override fun onHandleIntent(intent: Intent?) {

    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return START_REDELIVER_INTENT
    }

}
