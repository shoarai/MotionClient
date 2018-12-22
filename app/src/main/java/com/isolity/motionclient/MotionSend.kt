package com.isolity.motionclient

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

/**
 * Created by shohei52a on 2018/12/08.
 */

class MotionSend {

    val port = 8888
    val address = InetAddress.getByName("192.168.179.3")
    //    val address = InetAddress.getByName("192.168.179.255")
    //        val address = InetAddress.getByName("10.0.2.2")
    val datagramSocket = DatagramSocket(port)

    fun sendMotion(motion: Motion) {
        send("${motion.ax},${motion.ay},${motion.az}," +
                "${motion.wx},${motion.wy},${motion.wz}")
    }

    fun send(text: String) {
        val buff = text.toByteArray()
        try {
            datagramSocket.use({ socket ->
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