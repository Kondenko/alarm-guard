package com.kondenko.alarmguard.preferences

import android.content.Context
import android.content.res.TypedArray
import android.media.AudioManager
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceViewHolder
import android.util.AttributeSet
import android.widget.SeekBar
import com.kondenko.alarmguard.Constants
import com.kondenko.alarmguard.R
import com.pawegio.kandroid.audioManager
import com.pawegio.kandroid.i
import com.pawegio.kandroid.onProgressChanged

class MinVolumeSeekbarPreference(context: Context, attrs: AttributeSet) : Preference(context, attrs) {

    init {
        layoutResource = R.layout.pref_min_volume
    }


    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        super.onBindViewHolder(holder)
        val seekbar = holder?.findViewById(R.id.seekBarMinVolume) as SeekBar
        seekbar.max = context.audioManager?.getStreamMaxVolume(AudioManager.STREAM_ALARM) ?: Constants.DEFAULT_ALARM_VOLUME_MAX
        seekbar.progress = Preferences.prefMinVolume
        seekbar.onProgressChanged { value, fromUser -> Preferences.prefMinVolume = value }
    }

    override fun onGetDefaultValue(a: TypedArray, index: Int): Any {
        return a.getInteger(index, Constants.DEFAULT_ALARM_VOLUME)
    }

}