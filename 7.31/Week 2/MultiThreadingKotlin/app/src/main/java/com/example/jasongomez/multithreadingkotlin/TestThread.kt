package com.example.jasongomez.multithreadingkotlin

import android.os.Handler
import android.os.Looper
import android.widget.TextView

import org.greenrobot.eventbus.EventBus

class TestThread(private var tvTesting: TextView) : Thread() {

    var handler = Handler(Looper.getMainLooper())

    override fun run() {
        super.run()

        println(Thread.currentThread())
        for (i in 0 until 10) {

            try {
                Thread.sleep(1000)
                println(i)

            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            handler.post { tvTesting.text = i.toString() }

            EventBus.getDefault().post(MessageEvent(i.toString()))

        }


    }
}
