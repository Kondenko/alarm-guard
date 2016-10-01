package com.kondenko.alarmguard.preferences

import android.content.Context
import android.content.res.TypedArray
import android.support.v7.preference.SwitchPreferenceCompat
import android.util.AttributeSet
import com.kondenko.alarmguard.R

class IncreaseVolumeSwitchCompatPreference(context: Context, attrs: AttributeSet) : SwitchPreferenceCompat(context, attrs) {

    init {
        layoutResource = R.layout.layout_pref_increase_volume
    }

    override fun onAttached() {
        super.onAttached()
        this.isChecked = Preferences.increaseAutomatically
        setOnPreferenceChangeListener { preference, value -> onCheckedChange(!isChecked) }
    }

    private fun onCheckedChange(checked: Boolean): Boolean {
        Preferences.increaseAutomatically = checked
        return true
    }

    override fun onGetDefaultValue(a: TypedArray, index: Int): Any {
        return a.getBoolean(index, false)
    }

}