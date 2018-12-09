package com.isolity.motionclient

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener

/**
 * Created by shohei52a on 2018/12/08.
 */
class MotionEventListener : SensorEventListener {

    var onMotionChanged: ((Float, Float, Float, Int) -> Unit)? = null

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) {
            return
        }

        // 値が変化したセンサーが照度センサーだった場合
//        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//            onMotionChanged?.invoke(event.values[0], event.values[1], event.values[2])
//        }

        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                onMotionChanged?.invoke(
                        event.values[0],
                        event.values[1],
                        event.values[2],
                        event.sensor.type)
            }
            Sensor.TYPE_GYROSCOPE -> {
                onMotionChanged?.invoke(
                        event.values[0],
                        event.values[1],
                        event.values[2],
                        event.sensor.type)
            }
            else -> {
            }
        }

//        switch (sensorEvent.sensor.getType()){
//            case Sensor.TYPE_LIGHT:
//            //処理を記載
//            　　　　　　　　　　break;
//            case Sensor.TYPE_ACCELEROMETER:
//            　　　　　　　　　　//処理を記載
//            　　　　　　　　default:
//            　　　　　　　　　　//処理を記載
//            　　　　　　　　　　break;
//            　　　　　　}
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}