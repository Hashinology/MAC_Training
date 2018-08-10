package com.example.jasongomez.threadpoolexecutorpracticekotlin

import android.os.Message

import java.lang.ref.WeakReference
import java.util.ArrayList
import java.util.concurrent.BlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

object CustomThreadPoolManager {

    lateinit var uiHandler: WeakReference<MainActivity.UiHandler>
    private val executorService: ExecutorService
    private val taskQueue: BlockingQueue<Runnable>
    private val runningTaskList: MutableList<Future<*>>
    private const val NUMBER_OF_CORES = 4
    private const val KEEP_ALIVE_TIME = 1
    private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS


    init {
        taskQueue = LinkedBlockingQueue()
        runningTaskList = ArrayList()
        executorService = ThreadPoolExecutor(NUMBER_OF_CORES,
                NUMBER_OF_CORES,
                KEEP_ALIVE_TIME.toLong(),
                KEEP_ALIVE_TIME_UNIT,
                taskQueue)
    }

    fun getInstance(): CustomThreadPoolManager = this

    fun addRunnable() {
        val future = executorService.submit(CustomRunnable(this))
        runningTaskList.add(future)
    }

    fun cancelAllTasks() {
        taskQueue.clear()

        for (future in runningTaskList) {
            if (!future.isDone) {
                future.cancel(true)
            }
        }

        runningTaskList.clear()
    }

    fun postToUiThread(message: Message) {
        uiHandler.get()?.sendMessage(message)
    }

}
