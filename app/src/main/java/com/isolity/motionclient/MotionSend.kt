package com.isolity.motionclient

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

/**
 * Created by shohei52a on 2018/12/08.
 */

class MotionSend {

    var port = 8894
//        set
//    //    var address = InetAddress.getByName("192.168.179.3")
//    var address = InetAddress.getByName("10.0.2.2")
//    val datagramSocket = DatagramSocket(port)
//
//    fun setAddress(str: String) {
//        address = InetAddress.getByName(str)
//    }

    fun sendMotion(motion: Motion) {
        send("${motion.ax},${motion.ay},${motion.az}," +
                "${motion.wx},${motion.wy},${motion.wz}")
    }

    fun send(text: String) {
//        val port = 8891
//        val address = InetAddress.getByName("10.0.2.2")
        val address = InetAddress.getByName("192.168.179.3")
        val buff = text.toByteArray()
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