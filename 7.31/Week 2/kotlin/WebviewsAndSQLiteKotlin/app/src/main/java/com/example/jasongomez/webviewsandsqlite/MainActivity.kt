package com.example.jasongomez.webviewsandsqlite

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var etName: EditText
    private lateinit var etNumber: EditText

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etNumber = findViewById(R.id.etNumber)
        webView = findViewById(R.id.webview)

        val webViewCli = WebViewClient()
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.apply {
            webViewClient = webViewCli
            loadUrl("https://developer.android.com")
        }
    }

    fun saveContact(view: View) {

        val contact = MyContact(etName.text.toString(), etNumber.text.toString())
        val databaseHelper = DatabaseHelper(this)
        databaseHelper.saveNewContact(contact)


    }

    fun displayContact(view: View) {
        val databaseHelper = DatabaseHelper(this)
        val contacts = databaseHelper.contacts

        for (contact in contacts) {
            Log.d(TAG, "displayContact: " + contact.toString())
        }
    }
}


