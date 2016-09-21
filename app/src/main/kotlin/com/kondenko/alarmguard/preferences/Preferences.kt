package com.kondenko.alarmguard.preferences

import com.chibatching.kotpref.KotprefModel
import com.kondenko.alarmguard.Constants

/**
 * Manages app preferences
 */
object Preferences : KotprefModel() {

    // Determines if the app is checking alarm volume in the background
    var appEnabled: Boolean by booleanPrefVar(default = false)

    var defaultVolume: Int by intPrefVar(default = Constants.DEFAULT_ALARM_VOLUME)

    var increaseAutomatically: Boolean by booleanPrefVar(default = false)

}
