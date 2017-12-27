package com.veetaw.nospy.helper

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.veetaw.nospy.Constants
import com.veetaw.nospy.R
import com.veetaw.nospy.ui.MainActivity


class PersistentNotificationHelper {

    fun build(context: Context): Notification {
        val mNotifyIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, mNotifyIntent, 0)
        val notification = NotificationCompat.Builder(context, Constants().notificationChannelId)
                .setContentTitle(context.getString(R.string.service_running))
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher_round) //todo
                .build()
        val mNotifyMgr = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        mNotifyMgr.notify(1, notification)
        return notification
    }
}