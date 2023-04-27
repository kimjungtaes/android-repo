package com.example.test20130321_1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.example.test20130321_1.databinding.ActivityMain7Binding
import java.nio.channels.Channel

class MainActivity7 : AppCompatActivity() {
    lateinit var binding: ActivityMain7Binding
    lateinit var manager : NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNotificate.setOnClickListener {
            //1.notification 객체참조변수
            //1.notificationCompat
            manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder:  NotificationCompat.Builder

            //2.channel 객체참조변수를 생성(API 26버젼이상)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                //3.26버전이상이므로 채널객체참조변수

                val channelID: String = "kjt-channel"
                val channelName = "My kJT Channel"
                val channel = NotificationChannel("kjt-channel",channelName,NotificationManager.IMPORTANCE_HIGH)

                //채널에 대한 정보등록
                channel.description = "My KJT Channel Description"
                channel.setShowBadge (true)

                //알림음 오디오설정
                val notiificationUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributesBuilder =AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()

                channel.setSound(notiificationUri,audioAttributesBuilder)
                channel.enableLights(true)
                channel.lightColor = Color.RED
                channel.enableVibration(true)
                channel.vibrationPattern = longArrayOf(100,200,100,200)



                //4. 채널을 notificationManager에 등록
                manager.createNotificationChannel(channel)

                //5. 채널아이디를 이용하여  빌더 생성
                builder = NotificationCompat.Builder(this,channelID)
            }else{
                //5. 채널아이디를 이용하지않고 빌더를 생성
                builder = NotificationCompat.Builder(this)
            }

            //6. Builder 알림창이 어떤방법으로 구현할지 보여주기
            builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
            builder.setWhen(System.currentTimeMillis())
            builder.setContentTitle("My first Notification")
            builder.setContentText("My first Notification content")
            builder.setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.nature))

/*

            7. 알림이 발생후 터치시 내가 지정한 액티비티 화면으로 전환하는 팬딩이벤트
            7. 알림이 발생후 터치시 브로드캐스트 화면으로 정보를 보여준다.
//            val intent = Intent(this,DetailActivity::class.java)
//            val pendingIntent = PendingIntent.getActivity(this,10,intent, PendingIntent.FLAG_IMMUTABLE)
//            builder.setContentIntent(pendingIntent)
*/


            //8. 알림에 액션등록하기
        /* val actionIntent =  Intent(this,OneReceiver::class.java)
         val actionPendingIntent = PendingIntent.getBroadcast(this,20,actionIntent,PendingIntent.FLAG_IMMUTABLE)

            PendingIntent.getBroadcast(this, 20,actionIntent, PendingIntent.FLAG_IMMUTABLE)
         builder.addAction(NotificationCompat.Action.Builder(
         android.R.drawable.stat_notify_more,
         "Action",
             actionPendingIntent
         ).build()
         )*/

            //9.알림창에서 데이터를 입력하ㅏ면 해당되는 데이터를 브로드캐스트로 받아온다.
            //9-1.알림창에서 입력할수있는 기능부여
            val KEY_TEXT_REPLY = "kjt_noti_reply"
            val remoteInput:RemoteInput= RemoteInput.Builder("kjt_noti_reply").run {
                setLabel("답장해주세요")
                build()
            }
            val actionIntent =  Intent(this,OneReceiver::class.java)
            val actionPendingIntent = PendingIntent.getBroadcast(this,30,actionIntent,PendingIntent.FLAG_IMMUTABLE)
            builder.addAction(NotificationCompat.Action.Builder(
                R.drawable.send_24,
                "답장",
                actionPendingIntent
            ).build())




            //10. 매니저 알림
            manager.notify(11, builder.build())
        }

            binding.btnNotificateCancel.setOnClickListener {
                manager.cancel(11)
            }




    }
}//end of main