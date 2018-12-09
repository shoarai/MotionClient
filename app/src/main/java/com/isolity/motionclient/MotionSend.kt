package com.isolity.motionclient

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

/**
 * Created by shohei52a on 2018/12/08.
 */

class MotionSend {

    fun send(text: String) {
        val port = 8888
        val address = InetAddress.getByName("10.0.2.2")
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