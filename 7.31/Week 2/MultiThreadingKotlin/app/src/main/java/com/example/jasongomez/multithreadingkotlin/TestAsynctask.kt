package com.example.jasongomez.multithreadingkotlin

import android.os.AsyncTask
import android.util.Log

class TestAsynctask : AsyncTask<String, Int, String>() {

    companion object {
        private const val TAG = "Asynctask"
    }

    override fun onPreExecute() {
        super.onPreExecute()
        Log.d(TAG, "onPreExecute: " + Thread.currentThread())

    }

    override fun doInBackground(vararg strings: String): String {
        Log.d(TAG, "doInBackground: " + strings[0] + Thread.currentThread())
        publishProgress(1)
        return "result"
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        Log.d(TAG, "onProgressUpdate: " + Thread.currentThread())
    }

    override fun onPostExecute(s: String) {
        super.onPostExecute(s)

        Log.d(TAG, "onPostExecute: " + s + Thread.currentThread())

    }
}
