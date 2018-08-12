package com.example.jasongomez.webviewsandsqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

import java.util.ArrayList

class DatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABSE_VERSION) {

    companion object {
        private const val DATABSE_VERSION = 1
        private const val DATABASE_NAME = "MyDatabase"
        private const val TABLE_NAME = "Contacts"
        private const val CONTACT_NAME = "Name"
        private const val CONTACT_NUMBER = "Number"
        private const val TAG = "MyDBTag"
    }


    val contacts: List<MyContact>
        get() {

            val database = this.writableDatabase
            val query = "Select * from $TABLE_NAME"

            val cursor = database.rawQuery(query, null)

            val contacts = ArrayList<MyContact>()
            if (cursor.moveToFirst()) {
                do {
                    val contact = MyContact(cursor.getString(0), cursor.getString(1))
                    contacts.add(contact)
                } while (cursor.moveToNext())
            }

            return contacts
        }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {

        val CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                CONTACT_NAME + " TEXT," +
                CONTACT_NUMBER + " TEXT PRIMARY KEY" +
                ")"

        sqLiteDatabase.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(sqLiteDatabase)
    }

    fun saveNewContact(contact: MyContact) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(CONTACT_NAME, contact.name)
        contentValues.put(CONTACT_NUMBER, contact.number)
        val hadsaved = database.insert(TABLE_NAME, null, contentValues).toInt()

        Log.d(TAG, "saveNewContact: $hadsaved")

    }

    fun checkSingleton() {
        val singleton = ClassicSingleton.getInstance(context)


    }

}