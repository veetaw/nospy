package com.veetaw.nospy.util

import android.os.SystemClock
import android.util.Log

class ServiceThread constructor(private val prefs: Preferences) {
    private val audioUtil = Audio()

    fun run(): Thread {
        val t = Thread(Runnable {
            while (!Thread.currentThread().isInterrupted) {
                // check if audio card is used
                val isAudioListenerEnabled = prefs.getBool("audioListenerEnabled", false)
                if (isAudioListenerEnabled and audioUtil.isUsed()) {
                    //todo send notification
                    Log.d("ServiceThread", "audio card used")
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