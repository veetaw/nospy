package com.veetaw.nospy.helper

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.support.v4.app.NotificationCompat
import com.veetaw.nospy.Constants
import com.veetaw.nospy.R
import java.util.*

class NotificationHelper {
    fun build(context: Context, title: String): Notification {
        val notification = NotificationCompat.Builder(context, Constants().notificationChannelId)
                .setContentTitle(title)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.mipmap.ic_launcher_round) //todo
                .build()
        val mNotifyMgr = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotifyMgr.notify((Date().time / 1000L % Integer.MAX_VALUE).toInt(), notification)
        return notification
    }
}