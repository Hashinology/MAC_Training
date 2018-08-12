package com.example.jasongomez.webviewsandsqlite

import android.content.Context



object ClassicSingleton {

    private lateinit var context: Context

    fun getInstance(context: Context) {
        this.context = context
    }
}