package com.example.jasongomez.threadpoolexecutorpracticekotlin

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

import java.lang.ref.WeakReference
import java.util.concurrent.Executors

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar1: ProgressBar
    private lateinit var progressBar2: ProgressBar
    private lateinit var progressBar3: ProgressBar
    private lateinit var progressBar4: ProgressBar
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView
    private lateinit var textView4: TextView
    private lateinit var uiHandler: UiHandler
    private lateinit var customThreadPoolManager: CustomThreadPoolManager
    private lateinit var rxButton: Button
    private lateinit var scheduler: Scheduler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView1 = findViewById(R.id.tvThread1)
        textView2 = findViewById(R.id.tvThread2)
        textView3 = findViewById(R.id.tvThread3)
        textView4 = findViewById(R.id.tvThread4)
        progressBar1 = findViewById(R.id.progress1)
        progressBar2 = findViewById(R.id.progress2)
        progressBar3 = findViewById(R.id.progress3)
        progressBar4 = findViewById(R.id.progress4)
        rxButton = findViewById(R.id.btnAddCallableRx)
        scheduler = Schedulers.from(Executors.newFixedThreadPool(4))

        uiHandler = UiHandler(mainLooper,
                textView1,
                textView2,
                textView3,
                textView4,
                progressBar1,
                progressBar2,
                progressBar3,
                progressBar4)

        customThreadPoolManager = CustomThreadPoolManager.getInstance()
        customThreadPoolManager.uiHandler = WeakReference(uiHandler)
    }

    fun addCallable(view: View) {
        customThreadPoolManager.addRunnable()
    }

    fun addCallableRx(view: View) {
        Observable.create<View> { subscriber -> rxButton.setOnClickListener { subscriber.onNext(it) } }
                .observeOn(scheduler)
                .flatMap { Observable.just(1, 2, 3, 4) }
                .map { i ->
                    Thread.sleep(1000)
                    val message = ("Random int: $i \nThread ${Thread.currentThread().id} \nCompleted")
                    val bundle = Bundle()
                    bundle.putString("message", message)
                    bundle.putInt("progress", i)
                    bundle.putInt("whichThread", (Thread.currentThread().id % 4 + 1).toInt())
                    bundle
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Bundle> {

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(bundle: Bundle) {
                        val message = bundle.getString("message")
                        val i = bundle.getInt("progress")
                        val whichThread = bundle.getInt("whichThread")

                        when (whichThread) {
                            1 -> {
                                progressBar1.progress = i
                                textView1.text = message
                            }

                            2 -> {
                                progressBar2.progress = i
                                textView2.text = message
                            }

                            3 -> {
                                progressBar3.progress = i
                                textView3.text = message
                            }

                            4 -> {
                                progressBar4.progress = i
                                textView4.text = message
                            }
                        }
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })
    }

    class UiHandler(looper: Looper,
                    tvFirstThread: TextView,
                    tvSecondThread: TextView,
                    tvThirdThread: TextView,
                    tvFourthThread: TextView,
                    firstProgress: ProgressBar,
                    secondProgress: ProgressBar,
                    thirdProgress: ProgressBar,
                    fourthProgress: ProgressBar) : Handler(looper) {

        private val tvFirstThread: WeakReference<TextView> = WeakReference(tvFirstThread)
        private val tvSecondThread: WeakReference<TextView> = WeakReference(tvSecondThread)
        private val tvThirdThread: WeakReference<TextView> = WeakReference(tvThirdThread)
        private val tvFourthThread: WeakReference<TextView> = WeakReference(tvFourthThread)
        private val firstProgress: WeakReference<ProgressBar> = WeakReference(firstProgress)
        private val secondProgress: WeakReference<ProgressBar> = WeakReference(secondProgress)
        private val thirdProgress: WeakReference<ProgressBar> = WeakReference(thirdProgress)
        private val fourthProgress: WeakReference<ProgressBar> = WeakReference(fourthProgress)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            when (msg.what) {
                1 -> {
                    firstProgress.get()?.progress = msg.data.getInt("int")
                    tvFirstThread.get()?.text = msg.data.getString("message")
                }

                2 -> {
                    secondProgress.get()?.progress = msg.data.getInt("int")
                    tvSecondThread.get()?.text = msg.data.getString("message")
                }

                3 -> {
                    thirdProgress.get()?.progress = msg.data.getInt("int")
                    tvThirdThread.get()?.text = msg.data.getString("message")
                }

                4 -> {
                    fourthProgress.get()?.progress = msg.data.getInt("int")
                    tvFourthThread.get()?.text = msg.data.getString("message")
                }
            }
        }
    }
}

