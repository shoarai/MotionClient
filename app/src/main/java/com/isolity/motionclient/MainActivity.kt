package com.isolity.motionclient

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.ToggleButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    fun init() {
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener({
            onclickButton()
        })

        val sendToggleButton = findViewById<ToggleButton>(R.id.sendToggleButton)
        sendToggleButton.setOnCheckedChangeListener({ compoundButton, b ->

        })

    }

    fun onclickButton() {
        lisentenMotion()
//        sendAccelaration()
//        Thread(Runnable {
//            MotionSend().send("Hellow")
//        }).start()
    }


    fun onTime(motion: Motion) {
        showMotionInText(motion)
        sendMotion(motion)
    }

    fun sendMotion(motion: Motion) {
        Thread(Runnable {
            MotionSend().sendMotion(motion)
        }).start()
    }

    fun lisentenMotion() {
        val motionManager = MotionManager(applicationContext)
        motionManager.startListen()

        val delayMillis: Long = 2000
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                onTime(motionManager.motion)
                handler.postDelayed(this, delayMillis)
            }
        }, delayMillis)
    }

    private fun showMotionInText(motion: Motion) {
        val ax = findViewById<TextView>(R.id.axTextView)
        ax.text = motion.ax.toString()
        val ay = findViewById<TextView>(R.id.ayTextView)
        ay.text = motion.ay.toString()
        val az = findViewById<TextView>(R.id.azTextView)
        az.text = motion.az.toString()
        val wx = findViewById<TextView>(R.id.vxTextView)
        wx.text = motion.wx.toString()
        val wy = findViewById<TextView>(R.id.vyTextView)
        wy.text = motion.wy.toString()
        val wz = findViewById<TextView>(R.id.vzTextView)
        wz.text = motion.wz.toString()
    }

    fun sendAccelaration() {
        val mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val mSensor: Sensor? = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val gypro = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        val motionEventListener = MotionEventListener()
        motionEventListener.onMotionChanged = { accelarationX: Float, accelarationY: Float, accelarationZ: Float, sensorType: Int ->
            val ax = findViewById(R.id.axTextView) as TextView
            ax.text = accelarationX.toString()
            val ay = findViewById(R.id.ayTextView) as TextView
            ay.text = accelarationY.toString()
            val az = findViewById(R.id.azTextView) as TextView
            az.text = accelarationZ.toString()

            Thread(Runnable {
                MotionSend().send("${accelarationX},${accelarationY},${accelarationZ}")
            }).start()
        }
        mSensorManager.registerListener(motionEventListener, mSensor, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(motionEventListener, gypro, SensorManager.SENSOR_DELAY_GAME);


//        SensorManager.SENSOR_DELAY_FASTEST	0ms
//        SensorManager.SENSOR_DELAY_GAME	20ms
//        SensorManager.SENSOR_DELAY_UI	60ms
//        SensorManager.SENSOR_DELAY_NORMAL	200ms
    }
}
