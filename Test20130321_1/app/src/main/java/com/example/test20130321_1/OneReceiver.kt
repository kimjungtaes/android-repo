package com.example.test20130321_1

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.AppComponentFactory
import androidx.core.app.RemoteInput

class OneReceiver : BroadcastReceiver() {

    //callback함수:브로드캐스트 메세지가 전달오면 실행하는 callback함수
    override fun onReceive(context: Context, intent: Intent) {
     val reply_text =  RemoteInput.getResultsFromIntent(intent)?.getCharSequence("kjt_noti_reply")
    Log.e("OneReceive","알림창에서 전달된메세지${reply_text}")
        val manager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        manager.cancel(11)

    }
}