package com.veetaw.nospy.util

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import com.veetaw.nospy.R
import com.veetaw.nospy.helper.NotificationHelper
import com.veetaw.nospy.helper.PreferencesHelper


class ServiceThread constructor(private val context: Context) {
    private val audioUtil = Audio()
    private val prefs = PreferencesHelper(context)

    fun run(): Thread {
        val t = Thread(Runnable {
            var lastPid = 0
            if (prefs.getBool("cameraListenerEnabled", false)) {
                // we need to run this on the main thread, so we use a Handler
                Handler(Looper.getMainLooper()).post({
                    Camera().registerCallback(context)
                })
            }

            while (!Thread.currentThread().isInterrupted) {
                // check if audio card is used
                if (prefs.getBool("audioListenerEnabled", false) and audioUtil.isUsed()) {
                    val pid = audioUtil.getPID()
                    if (lastPid != pid)
                        NotificationHelper().build(context, context.getString(R.string.audio_card_used))
                    lastPid = pid
                }
                SystemClock.sleep(2000)
            }
        })

        t.start()
        return t
    }
}