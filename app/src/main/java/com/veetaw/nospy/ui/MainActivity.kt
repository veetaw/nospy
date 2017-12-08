package com.veetaw.nospy.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Switch
import com.veetaw.nospy.R
import com.veetaw.nospy.helper.PreferencesHelper
import com.veetaw.nospy.service.Service

class MainActivity : AppCompatActivity() {

    private var prefs: PreferencesHelper? = null
    private var audioSwitch: Switch? = null
    //var cameraSwitch : Switch? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = PreferencesHelper(this)

        audioSwitch = findViewById(R.id.audio_switch)
        //cameraSwitch = findViewById(R.id.camera_switch)

        initPrefs()
        setOnClickListeners()

        startService(Intent(this, Service::class.java)) //todo

    }

    private fun initPrefs() {
        if (!prefs!!.prefsExist()) {
            prefs!!.setBool("audioListenerEnabled", false)
            //prefs.setBool("cameraListenerEnabled", false)
        }
        audioSwitch?.isChecked = prefs!!.getBool("audioListenerEnabled", false)
        //cameraSwitch?.isChecked = prefs.getBool("cameraListenerEnabled", false)
    }

    private fun setOnClickListeners() {
        audioSwitch?.setOnClickListener {
            prefs!!.setBool("audioListenerEnabled", audioSwitch!!.isChecked)
        }
        /* todo
        *cameraSwitch?.setOnClickListener {
        *    prefs!!.setBool("cameraListenerEnabled", cameraSwitch!!.isChecked)
        *}
        */
    }

}
