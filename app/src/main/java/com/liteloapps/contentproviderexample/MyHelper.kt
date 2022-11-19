
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelper(context: Context?) : SQLiteOpenHelper(context, "sqlDB", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE CONTACT (contactId INTEGER PRIMARY KEY AUTOINCREMENT, contactName TEXT, contactNumber TEXT)")
        db?.execSQL("INSERT INTO CONTACT(contactName, contactNumber) VALUES ('Seth Rollins', '9873456789')")
        db?.execSQL("INSERT INTO CONTACT(contactName, contactNumber) VALUES ('Brock Lesner', '9875432167')")
        db?.execSQL("INSERT INTO CONTACT(contactName, contactNumber) VALUES ('Kevin Owens', '9802178394')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}
}