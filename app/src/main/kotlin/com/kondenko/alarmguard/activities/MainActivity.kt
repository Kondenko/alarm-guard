package com.kondenko.alarmguard.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.preference.PreferenceScreen
import android.support.v7.widget.SwitchCompat
import android.view.Menu
import com.kondenko.alarmguard.R
import com.kondenko.alarmguard.preferences.PrefFragment
import com.kondenko.alarmguard.preferences.Preferences
import com.kondenko.alarmguard.utils.VolumeCheckerService

class MainActivity : AppCompatActivity(), PreferenceFragmentCompat.OnPreferenceStartScreenCallback {

    override fun onPreferenceStartScreen(preferenceFragmentCompat: PreferenceFragmentCompat?, preferenceScreen: PreferenceScreen?): Boolean {
        preferenceFragmentCompat?.preferenceScreen = preferenceScreen
        return true
    }

    lateinit var service: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        service = Intent(this, VolumeCheckerService::class.java)
        enableApp(Preferences.appEnabled)
        supportFragmentManager.beginTransaction().replace(R.id.container, PrefFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        val item = menu?.findItem(R.id.action_switch_enable)
        item?.setActionView(R.layout.layout_switch)
        val switch = item?.actionView as SwitchCompat
        switch.isChecked = Preferences.appEnabled
        switch.setOnCheckedChangeListener({
            compoundButton, checked ->
            enableApp(checked)
        })
        return true
    }

    private fun enableApp(enable: Boolean) {
        Preferences.appEnabled = enable
        if (enable) {
            startService(service)
        } else {
            stopService(service)
        }
    }


}
