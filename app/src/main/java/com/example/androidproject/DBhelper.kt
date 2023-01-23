package com.example.sqlite_db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.androidproject.ModelData.ApiMD
import com.example.androidproject.ModelData.ApiMDItem

class DBhelper(context: Context?) : SQLiteOpenHelper(context, "ApiDetails", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "CREATE TABLE ApiData(id INTEGER PRIMARY KEY AUTOINCREMENT ,title TEXT ,albumid TEXT ,url TEXT,thumbnailUrl TEXT)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertData(
        TITLE: String,
        ALBUMID: String,
        URL: String,
        THUMBNAILURL: String,
    ) {
        val db = writableDatabase

        val contentValues = ContentValues()
        contentValues.put("title", TITLE)
        contentValues.put("albumid", ALBUMID)
        contentValues.put("url", URL)
        contentValues.put("thumbnailUrl", THUMBNAILURL)

        db.insert("ApiData", null, contentValues)
    }

    @SuppressLint("Range")
    fun readData(): ArrayList<ApiMDItem> {
        val dataList = arrayListOf<ApiMDItem>()
        val db = readableDatabase
        val query = "SELECT * FROM ApiData"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(cursor.getColumnIndex("id")).toString()
                val title = cursor.getString(cursor.getColumnIndex("title")).toString()
                val albumId = cursor.getString(cursor.getColumnIndex("albumid")).toString()
                val url = cursor.getString(cursor.getColumnIndex("url")).toString()
                val thumbnailUrl = cursor.getString(cursor.getColumnIndex("thumbnailUrl")).toString()
                val d1 = ApiMDItem(albumId, id, title, url, thumbnailUrl)
                dataList.add(d1)

            } while (cursor.moveToNext())
        }
        return dataList
    }

}
