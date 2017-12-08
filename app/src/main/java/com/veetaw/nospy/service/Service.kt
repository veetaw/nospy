package com.veetaw.nospy.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.veetaw.nospy.util.PersistentNotification
import com.veetaw.nospy.util.ServiceThread

class Service : Service() {
    private var t: Thread? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        startForeground(1, PersistentNotification().build(this))

        t = ServiceThread(this).run()

        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSelf()
        stopForeground(true)
        t!!.interrupt()
    }
}