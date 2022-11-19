package com.liteloapps.contentproviderexample

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var name: EditText
    lateinit var number: EditText
    lateinit var contactList: TextView
    lateinit var insertButton: Button
    lateinit var getButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.contact_name)
        number = findViewById(R.id.contact_number)
        insertButton = findViewById(R.id.button)
        contactList = findViewById(R.id.textView2)
        getButton = findViewById(R.id.getListBtn)

        var uri: Uri = ContactListProvider.CONTENT_URI

        insertButton.setOnClickListener {
            var values = ContentValues()
            values.put(ContactListProvider.name, name.text.toString())
            values.put(ContactListProvider.number, number.text.toString())

            uri = contentResolver.insert(ContactListProvider.CONTENT_URI, values )!!
        }

        var str: String = ""
        getButton.setOnClickListener {

            var cursor: Cursor? = contentResolver.query(uri,
                arrayOf(ContactListProvider.id, ContactListProvider.name, ContactListProvider.number),
                null,null,null )


            while(cursor?.moveToNext() == true) {
                str = str + cursor.getString(1) +" : " + cursor.getString(2) + "\n"
            }
            contactList.setText(str)
        }


    }
}