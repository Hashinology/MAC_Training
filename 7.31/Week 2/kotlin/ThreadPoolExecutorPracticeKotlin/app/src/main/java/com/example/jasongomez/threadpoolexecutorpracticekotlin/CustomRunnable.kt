package com.example.jasongomez.threadpoolexecutorpracticekotlin

import android.os.Bundle
import android.os.Message
import java.lang.ref.WeakReference

class CustomRunnable(customThreadPoolManagerWeakReference: CustomThreadPoolManager) : Runnable {

    private val customThreadPoolManagerWeakReference: WeakReference<CustomThreadPoolManager> = WeakReference(customThreadPoolManagerWeakReference)

    override fun run() {
        try {

            for (i in 1..4) {
                Thread.sleep(1000)
                val message = ("Random int: $i \nThread ${Thread.currentThread().id} \nCompleted")

                val bundle = Bundle()
                bundle.putString("message", message)
                bundle.putInt("int", i)
                val message1 = Message()
                message1.data = bundle
                message1.what = (Thread.currentThread().id % 4 + 1).toInt()
                customThreadPoolManagerWeakReference.get()?.postToUiThread(message1)
            }

        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }
}