package com.example.firebasetest2304195

import com.bumptech.glide.Glide.init
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class PictureDAO {
    var databaseReference : DatabaseReference? = null
    var storage: FirebaseStorage? = null

    init {
        //real time database 연결
        val db = FirebaseDatabase.getInstance()
        //user table 생성객체  생각
        databaseReference = db.getReference("picture")
        storage  = Firebase.storage
    }

    //insert into user valuse(-,-,-,-)
    fun pictureInsert (pictureData: PictureData) : Task<Void> {
        return databaseReference!!.push(). setValue(pictureData)
    }

    //select * from user
    fun pictureSelect(): Query?{
        return databaseReference
    }
}