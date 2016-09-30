package com.kondenko.alarmguard.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.preference.PreferenceScreen
import android.support.v7.widget.SwitchCompat
import android.view.Menu
import android.widget.FrameLayout
import com.kondenko.alarmguard.R
import com.kondenko.alarmguard.preferences.PrefFragment
import com.kondenko.alarmguard.preferences.Preferences
import com.kondenko.alarmguard.utils.VolumeCheckerService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_card.view.*

class MainActivity : AppCompatActivity(), PreferenceFragmentCompat.OnPreferenceStartScreenCallback {

    lateinit var service: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        processFirstRun {
            setupCardView()
        }
        service = Intent(this, VolumeCheckerService::class.java)
        supportFragmentManager.beginTransaction().replace(R.id.container, PrefFragment()).commit()
        enableApp(Preferences.appEnabled)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        val item = menu?.findItem(R.id.action_switch_enable)
        item?.setActionView(R.layout.layout_pref_switch)
        val switch = item?.actionView as SwitchCompat
        switch.isChecked = Preferences.appEnabled
        switch.setOnCheckedChangeListener({
            compoundButton, checked ->
            enableApp(checked)
        })
        return true
    }

    override fun onPreferenceStartScreen(preferenceFragmentCompat: PreferenceFragmentCompat?, preferenceScreen: PreferenceScreen?): Boolean {
        preferenceFragmentCompat?.preferenceScreen = preferenceScreen
        return true
    }

    private fun enableApp(enable: Boolean) {
        Preferences.appEnabled = enable
        if (enable) startService(service)
        else stopService(service)
    }

    private fun setupCardView() {
        val card = layoutInflater.inflate(R.layout.layout_card, layout_main, false)
        val width = FrameLayout.LayoutParams.MATCH_PARENT
        val height = FrameLayout.LayoutParams.WRAP_CONTENT
        val layoutParamsCard = FrameLayout.LayoutParams(width, height)
        layout_main.addView(card, 0, layoutParamsCard)
        card.button_dismiss_welcome_card.setOnClickListener {
            layout_main.removeView(card)
        }
    }

    private fun processFirstRun(action: () -> Unit) {
        if (Preferences.isFirstRun) {
            action()
            Preferences.isFirstRun = false
        }
    }
}


