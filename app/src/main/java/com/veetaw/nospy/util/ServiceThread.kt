package com.veetaw.nospy.util

import android.content.Context
import android.os.SystemClock
import com.veetaw.nospy.R
import com.veetaw.nospy.helper.NotificationHelper
import com.veetaw.nospy.helper.PreferencesHelper

class ServiceThread constructor(private val context: Context) {
    private val audioUtil = Audio()
    private val prefs = PreferencesHelper(context)

    fun run(): Thread {
        val t = Thread(Runnable {
            while (!Thread.currentThread().isInterrupted) {
                // check if audio card is used
                val isAudioListenerEnabled = prefs.getBool("audioListenerEnabled", false)
                if (isAudioListenerEnabled and audioUtil.isUsed()) {
                    val pid = audioUtil.getPID()
                    NotificationHelper().build(context, context.getString(R.string.audio_card_used) +
                            PidUtil().packageNameByPID(context, pid) + " (" + pid.toString() + ")")
                }

                //check if camera is used
                //if (isCameraListenerEnabled and cameraUtil.isUsed()) {
                //  //todo send notification
                //  Log.d("debug", "camera used")
                //}
                SystemClock.sleep(2000)
            }
        })

        t.start()
        return t
    }
}