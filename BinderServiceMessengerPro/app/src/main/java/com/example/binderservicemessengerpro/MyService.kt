package com.example.binderservicemessengerpro

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.Toast

class MyService : Service() {
    //1.메신저 객체 참조 변수 선언
    lateinit var messenger: Messenger

    //2.IBinder class (내가 준비해놓은 IBinder)
    internal class IncommingHandler(
        context: Context,
        val applicationContext: Context = context.applicationContext
    ) : Handler(
        Looper.getMainLooper()
    ) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                100 -> {
                    Toast.makeText(applicationContext, "${msg.obj}", Toast.LENGTH_SHORT).show()
                    Log.e("MyService", "MyService = ${msg.obj}")
                }
                200 -> {
                    Toast.makeText(applicationContext, "${msg.obj}", Toast.LENGTH_SHORT).show()
                    Log.e("MyService", "MyService = ${msg.obj}")
                }
                else -> {
                    Toast.makeText(applicationContext, "프로토콜을 점검요망", Toast.LENGTH_SHORT).show()
                    Log.e("MyService", "MyService = 프로토콜 점검요망")
                }
            }

        }
    }

    override fun onBind(intent: Intent): IBinder {
        messenger = Messenger(IncommingHandler(this))
        return messenger.binder
    }
}