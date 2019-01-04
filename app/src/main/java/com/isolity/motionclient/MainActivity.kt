package com.isolity.motionclient

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private val startButton by lazy {
        findViewById<Button>(R.id.startButton)
    }
    private val stopButton by lazy {
        findViewById<Button>(R.id.stopButton)
    }
    private val ipAddressText by lazy {
        findViewById<TextView>(R.id.ipAddressText)
    }
    private val portText by lazy {
        findViewById<TextView>(R.id.portText)
    }

    private val motionManager by lazy {
        MotionManager(applicationContext)
    }
    val motionSender = MotionSend()

    private fun init() {
        startButton.setOnClickListener({
            onclickStartButton()
        })
        stopButton.setOnClickListener({
            stopListenMotion()
        })
    }

    fun onclickStartButton() {
        startLisentenMotion()
    }

    fun onTime(motion: Motion) {
        sendMotion(motion)
        showMotionInText(motion)
    }

    fun sendMotion(motion: Motion) {
        Thread(Runnable {
            motionSender.sendMotion(motion)
        }).start()
    }

    var running: Boolean = false

    private fun startLisentenMotion() {
//        motionSender.setAddress(ipAddressText.text.toString())
        motionSender.port = Integer.parseInt(portText.text.toString())
        running = true
        motionManager.startListen()

        val delayMillis: Long = 100
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (!running) {
                    return
                }
                onTime(motionManager.motion)
                handler.postDelayed(this, delayMillis)
            }
        }, delayMillis)

        startButton.visibility = View.GONE
        stopButton.visibility = View.VISIBLE
    }

    private fun stopListenMotion() {
        running = false
        motionManager.stopListen()

        startButton.visibility = View.VISIBLE
        stopButton.visibility = View.GONE
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
}
