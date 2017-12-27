package com.veetaw.nospy.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Switch
import android.widget.Toast
import com.veetaw.nospy.Constants
import com.veetaw.nospy.R
import com.veetaw.nospy.helper.PreferencesHelper
import com.veetaw.nospy.service.Service

class MainActivity : AppCompatActivity() {

    private var prefs: PreferencesHelper? = null
    private val c = Constants()

    private var audioSwitch: Switch? = null
    private var cameraSwitch: Switch? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = PreferencesHelper(this)

        audioSwitch = findViewById(R.id.audio_switch)
        cameraSwitch = findViewById(R.id.camera_switch)

        initPrefs()
        setOnClickListeners()

        startService(Intent(this, Service::class.java)) //todo

    }

    private fun initPrefs() {
        if (!prefs!!.prefsExist()) {
            prefs!!.setBool(c.audioPrefName, false)
            prefs!!.setBool(c.cameraPrefName, false)
        }
        audioSwitch?.isChecked = prefs!!.getBool(c.audioPrefName, false)
        cameraSwitch?.isChecked = prefs!!.getBool(c.cameraPrefName, false)
    }

    private fun setOnClickListeners() {
        audioSwitch?.setOnClickListener {
            prefs!!.setBool(c.audioPrefName, audioSwitch!!.isChecked)
            Toast.makeText(this, getString(R.string.restart_app), Toast.LENGTH_LONG).show()
        }
        cameraSwitch?.setOnClickListener {
            prefs!!.setBool(c.cameraPrefName, cameraSwitch!!.isChecked)
            Toast.makeText(this, getString(R.string.restart_app), Toast.LENGTH_LONG).show()
        }
    }

}
