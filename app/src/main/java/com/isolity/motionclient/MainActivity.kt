package com.isolity.motionclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    fun init() {
        val button = findViewById(R.id.button) as Button
        button.setOnClickListener({
            onclickButton()
        })
    }

    fun onclickButton() {
        Thread(Runnable {
            send()
        }).start()
    }

    fun send() {
        val port = 8888
        val address = InetAddress.getByName("10.0.2.2")
        val buff = "Hellow".toByteArray()
        try {
            DatagramSocket(port).use({ socket ->
                val packet = DatagramPacket(
                        buff,
                        buff.size,
                        address,
                        port
                )
                socket.send(packet)
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
