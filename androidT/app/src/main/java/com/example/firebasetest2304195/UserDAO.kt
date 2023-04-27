package com.example.firebasetest2304195

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query

class UserDAO {
    private  var databaseReference : DatabaseReference? = null

    init {
        //real time database 연결
        val db = FirebaseDatabase.getInstance()
        //user table 생성객체  생각
        databaseReference = db.getReference("user")

    }

    //insert into user valuse(-,-,-,-)
    fun fbInsert (user: User?) : Task<Void>{
        return databaseReference!!.push(). setValue(user)
    }

    //select * from user
    fun userSelect(): Query?{
        return databaseReference
    }

    //updata set user set userKey = "~~~~",
    fun userUpdate(userkey:String, hashMap: HashMap<String, Any>): Task<Void>{
        return  databaseReference!!.child(userkey). updateChildren(hashMap)
    }

    //delete from user where userKey = ?
    fun userDelete(userkey: String) : Task<Void>{
        return databaseReference!!.child(userkey) . removeValue()
    }

}