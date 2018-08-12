package com.example.jasongomez.multithreadingkotlin

import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity(), Handler.Callback {

    private lateinit var tvTesting: TextView
    private lateinit var tvTestTHM: TextView

    companion object {
        private const val TAG = "MainActivityTag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTesting = findViewById(R.id.tvTesting)
        tvTestTHM = findViewById(R.id.tvThreadHandlerMessage)

    }

    fun executeThreads(view: View) {

        when (view.id) {
            R.id.btnThread -> {
                val testThread = TestThread(tvTesting)
                testThread.start()
            }

            R.id.btnRunnable -> {
                val testRunnable = TestRunnable(tvTesting)
                val thread = Thread(testRunnable)
                thread.start()
            }
            R.id.btnAsyntask -> {
                val testAsynctask = TestAsynctask()
                testAsynctask.execute("Starting")
            }
            R.id.btnThreadHandlerMessage -> {
                //creating a new handler with the callback
                val handler = Handler(Handler.Callback { message ->
                    Log.d(TAG, "handleMessage: from Handler")
                    tvTestTHM.text = message.data.getString("KEY_DATA")
                    false
                })


                //Using Handler
                val testThreadHandlerMessageH = TestThreadHandlerMessage(handler)
                testThreadHandlerMessageH.start()

                //create the handler by implementing the callback in the class
                val handler1 = Handler(this)

                //Using handler1
                val testThreadHandlerMessageH1 = TestThreadHandlerMessage(handler1)
                testThreadHandlerMessageH1.start()


                //create the handler by implementing the callback in the class
                val handler2 = Handler(CallbackHandler())

                //Using handler2
                val testThreadHandlerMessageH2 = TestThreadHandlerMessage(handler2)
                testThreadHandlerMessageH2.start()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)

    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(messageEvent: MessageEvent) {
        Toast.makeText(this, messageEvent.message, Toast.LENGTH_SHORT).show()

    }

    override fun handleMessage(message: Message): Boolean {
        tvTestTHM.text = message.data.getString("KEY_DATA")
        Log.d(TAG, "handleMessage: from Handler1")
        return false
    }
}
