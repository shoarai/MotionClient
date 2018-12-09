package com.isolity.motionclient

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager

/**
 * Created by shohei52a on 2018/12/08.
 */
class MotionManager {
    val motion: Motion
    val sensorManager: SensorManager
    val motionEventListener: MotionEventListener

    constructor(context: Context) {
        motion = Motion()
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        motionEventListener = MotionEventListener()
        setSensorEventListener()
    }

    fun startListen() {
        val accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val gypro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        sensorManager.registerListener(motionEventListener, accel, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(motionEventListener, gypro, SensorManager.SENSOR_DELAY_GAME);
    }

    private fun setSensorEventListener() {
        motionEventListener.onMotionChanged = { x: Float, y: Float, z: Float, sensorType: Int ->
            when (sensorType) {
                Sensor.TYPE_ACCELEROMETER -> {
                    motion.ax = x
                    motion.ay = y
                    motion.az = z
                }
                Sensor.TYPE_GYROSCOPE -> {
                    motion.wx = x
                    motion.wy = y
                    motion.wz = z
                }
            }
        }
    }

    fun stopListen() {
        sensorManager.unregisterListener(motionEventListener);
    }
}