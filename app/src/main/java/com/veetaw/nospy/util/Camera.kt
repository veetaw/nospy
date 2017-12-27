package com.veetaw.nospy.util

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import com.veetaw.nospy.R
import com.veetaw.nospy.helper.NotificationHelper

class Camera {
    private var cameraManager: CameraManager? = null

    fun registerCallback(context: Context) {
        cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

        cameraManager!!.registerAvailabilityCallback(object : CameraManager.AvailabilityCallback() {

            // onCameraAvailable is useless

            override fun onCameraUnavailable(cameraId: String) {
                super.onCameraUnavailable(cameraId)
                if (isFrontCamera(cameraManager!!.getCameraCharacteristics(cameraId)))
                    NotificationHelper().build(context, context.getString(R.string.front_camera_used))
                else
                    NotificationHelper().build(context, context.getString(R.string.camera_used))
            }
        }, null)
    }

    private fun isFrontCamera(c: CameraCharacteristics): Boolean {
        return c.get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_FRONT
    }
}