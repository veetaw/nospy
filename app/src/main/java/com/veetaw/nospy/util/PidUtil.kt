package com.veetaw.nospy.util

import android.app.ActivityManager
import android.content.Context


class PidUtil {
    fun packageNameByPID(context: Context, pid: Int): String {
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        manager.runningAppProcesses
                .filter { it.pid == pid }
                .forEach { return it.processName }
        return ""

    }
}
