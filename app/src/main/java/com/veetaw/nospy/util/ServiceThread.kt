package com.veetaw.nospy.util

import android.content.Context
import android.os.SystemClock
import android.util.Log

class ServiceThread constructor(private val context: Context) {
    private val audioUtil = Audio()
    private val prefs = Preferences(context)

    fun run(): Thread {
        val t = Thread(Runnable {
            while (!Thread.currentThread().isInterrupted) {
                // check if audio card is used
                val isAudioListenerEnabled = prefs.getBool("audioListenerEnabled", false)
                if (isAudioListenerEnabled and audioUtil.isUsed()) {
                    //todo send notification
                    Log.d("ServiceThread", "audio card used by " +
                            PidUtil().packageNameByPID(context, audioUtil.getPID()))
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