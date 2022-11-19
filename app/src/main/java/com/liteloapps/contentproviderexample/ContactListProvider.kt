package com.liteloapps.contentproviderexample

import MyHelper
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

const val tableName = "CONTACT"

class ContactListProvider : ContentProvider() {

    companion object {
        val PROVIDER_NAME = "com.liteloapps.contentproviderexample/ContactListProvider"
        val URL = "content://$PROVIDER_NAME/CONTACT"
        val CONTENT_URI: Uri = Uri.parse(URL)

        val id = "contactId"
        val name = "contactName"
        val number = "contactNumber"
    }

    lateinit var database: SQLiteDatabase

    override fun onCreate(): Boolean {
        var helper = MyHelper(context)
        database = helper.writableDatabase

        return database != null
    }

    override fun insert(uri: Uri, contentValues: ContentValues?): Uri {
        database.insert(tableName, null, contentValues)
        context?.contentResolver?.notifyChange(uri, null)
        return uri
    }

    override fun update(uri: Uri, contentValues: ContentValues?, condition: String?, conditionValue: Array<out String>?): Int {
        var count = database.update(tableName, contentValues, condition, conditionValue)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun delete(uri: Uri, condition: String?, conditionValues: Array<out String>?): Int {
        var count = database.delete(tableName, condition, conditionValues)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun query(
        uri: Uri,
        cols: Array<out String>?,
        condition: String?,
        conditionValues: Array<out String>?,
        order: String?
    ): Cursor? {
        return database.query(tableName, cols, condition, conditionValues, null,null, order)
    }

    override fun getType(p0: Uri): String? {
        return "vnd.android.cursor.dir/vnd.com.liteloapps.contentproviderexample.$tableName"
    }
}