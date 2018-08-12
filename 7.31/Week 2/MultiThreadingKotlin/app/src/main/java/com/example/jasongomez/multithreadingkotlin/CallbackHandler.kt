package com.example.jasongomez.multithreadingkotlin

import android.os.Handler
import android.os.Message

class CallbackHandler : Handler.Callback {


    override fun handleMessage(message: Message): Boolean {
        println("CallbackHandler" + message.data.getString("KEY_DATA"))
        return false
    }
}