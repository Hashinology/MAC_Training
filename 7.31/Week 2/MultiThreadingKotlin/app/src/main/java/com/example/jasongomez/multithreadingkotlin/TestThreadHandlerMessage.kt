package com.example.jasongomez.multithreadingkotlin

import android.os.Bundle
import android.os.Handler
import android.os.Message

class TestThreadHandlerMessage(private var handler: Handler) : Thread() {

    override fun run() {
        super.run()
        val data = "Message from TestThreadHandlerMessage"
        println("run: Thread: " + Thread.currentThread() + "Data: " + data)

        //Adding the data to be sent to the bundle
        val bundle = Bundle()
        bundle.putString("KEY_DATA", data)

        //Add the bundle to the message object
        val message = Message()
        message.data = bundle

        //send the message object to the handler
        handler.sendMessage(message)

    }
}