import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.libraryretrofitpro.LibraryData

class DBHelper(val context: Context?, val name: String?, val version: Int) :
    SQLiteOpenHelper(context, name, null, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(
                "create table libraryTBL(" +
                        "code text primary key," +
                        "name text," +
                        "phone text," +
                        "address text ," +
                        "latitude text," +
                        "longitude text)"
            )
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists libraryTBL")
        this.onCreate(db)
        db?.close()
    }

    fun insertTBL(data: LibraryData): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        var flag = false
        try {
            db.execSQL(
                "insert into libraryTBL values( '${data.code}','${data.name}','${data.phone}'," +
                        " '${data.adress}' , '${data.latitude}', '${data.longitude}')"
            )
            Log.d("DBHelper", "insert 성공")
            flag = true
        } catch (e: SQLException) {
            flag = false
            Log.e("DBHelper", "${e.message}")
        }
        return flag
    }

    fun selectAll(): MutableList<LibraryData>? {
        val db: SQLiteDatabase = this.readableDatabase
        var cursor: Cursor? = null
        var mutableList: MutableList<LibraryData>? = mutableListOf()
        try {
            cursor = db.rawQuery("select * from libraryTBL", null)
            if (cursor.count >= 1) {
                while (cursor.moveToNext()) {
                    val libraryData = LibraryData(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                    )
                    mutableList?.add(libraryData)
                    Log.d("DBHelper", "selectAll 성공")

                }
            }

        } catch (e: SQLException) {
            Log.d("DBHelper", "selectAll 예외 발생 ${e.printStackTrace()}")
        }
        return mutableList
    }

    fun deleteAll(): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        var flag = false
        try {
            db.execSQL("delete from libraryTBL")
            Log.e("DBHelper", "delete   성공")
            flag = true
        } catch (e: SQLException) {
            Log.e("DBHelper", "delete 예외발생 ${e.printStackTrace()}")
        }
        return flag
    }
}

