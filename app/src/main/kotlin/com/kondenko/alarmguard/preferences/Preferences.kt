package com.kondenko.alarmguard.preferences

import android.content.Context
import com.chibatching.kotpref.KotprefModel
import com.kondenko.alarmguard.Constants
import com.kondenko.alarmguard.R

/**
 * Manages app preferences
 */
object Preferences : KotprefModel() {

    var isFirstRun: Boolean by booleanPrefVar(default = true)

    var appEnabled: Boolean by booleanPrefVar(default = false)

    var defaultVolume: Int by intPrefVar(default = Constants.DEFAULT_ALARM_VOLUME)

    var increaseAutomatically: Boolean by booleanPrefVar(default = false, key = context.getString(R.string.pref_key_increase_volume))

}
