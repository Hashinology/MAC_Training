package com.example.jasongomez.multithreadingkotlin

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView

class TestRunnable(private var textView: TextView) : Runnable {

    private var handler = Handler(Looper.getMainLooper())

    override fun run() {
        println(Thread.currentThread())

        for (i in 1 until 5) {

            try {
                Thread.sleep(1000)
                println(i.toString())
                Log.d("TAG", "run: $i")

                handler.postDelayed({ textView.text = i.toString() }, 2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}
