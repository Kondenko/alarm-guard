package com.kondenko.alarmguard.preferences

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import com.kondenko.alarmguard.R

class PrefFragment() : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        val switchForceVolume = preferenceScreen.getPreference(1)
        switchForceVolume.setOnPreferenceChangeListener { preference, value -> changeValue(value as Boolean) }
    }

    private fun changeValue(value: Boolean): Boolean {
        Preferences.increaseAutomatically = value
        return true
    }

}