package com.example.homemymp3

import android.graphics.Bitmap
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import com.example.homemymp3.databinding.ActivityPlayBinding
import kotlinx.coroutines.*
import java.text.SimpleDateFormat

class PlayActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityPlayBinding
    lateinit var musicData: MusicData
    private var playList: MutableList<Parcelable>? = null
    private var currentposition: Int = 0
    val ALBUM_IMAGE_SIZE = 90
    var mediaPlayer: MediaPlayer? = null
    var mp3playerJob: Job? = null
    var pauseFlag = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 전달해온 intent 값을 가져옴
        playList = intent.getParcelableArrayListExtra("parcelableList")
        currentposition = intent.getIntExtra("position", 0)
        musicData = playList?.get(currentposition) as MusicData

        // view binding 진행 (activity_play와  MusicData 값을 이어준다)
        binding.albumtitle.text = musicData.title
        binding.albumartist.text = musicData.artist
        binding.totalduration.text = SimpleDateFormat("mm:ss").format(musicData.duration)
        binding.playduration.text = "00:00"
        val bitmap = musicData.getAlbumBitmap(this, ALBUM_IMAGE_SIZE)
        if (bitmap != null) {
            binding.albumImage.setImageBitmap(bitmap)
        } else {
            binding.albumImage.setImageResource(R.drawable.music_24)
        }

        // 음악 파일객체 가져오도록 한다
        mediaPlayer = MediaPlayer.create(this, musicData.getMusicUri())

        // 이벤트 처리(pasue, play, back, stop, seekbar 조절)
        binding.listbutton.setOnClickListener(this)
        binding.playbutton.setOnClickListener(this)
        binding.nextmusicbutton.setOnClickListener(this)
        binding.backmusicbutton.setOnClickListener(this)
        binding.seekBar.max = mediaPlayer!!.duration
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    // seekTo 해당하는 play 위치로 이동
                    mediaPlayer?.seekTo(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.listbutton -> {
                mp3playerJob?.cancel()
                mediaPlayer?.stop() //stop
                mediaPlayer?.release() // 할당된 메모리해제
                mediaPlayer = null
                finish() //end
            }
            R.id.playbutton -> {
                if (mediaPlayer!!.isPlaying) {
                    mediaPlayer?.pause() // 멈추기
                    binding.playbutton.setImageResource(R.drawable.play_24)
                    pauseFlag = true
                } else {
                    //music play
                    mediaPlayer?.start()
                    binding.playbutton.setImageResource(R.drawable.pause_24)
                    pauseFlag = false

                    // coroutine으로 music을 play
                    val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
                    mp3playerJob = backgroundScope.launch {
                        while (mediaPlayer!!.isPlaying) {
                            var currentPosition = mediaPlayer?.currentPosition!!
                            // coroutine속에서 화면의 값을 변동시키고자 할 때 runOnUiThread 사용
                            var strCurrentPosition = SimpleDateFormat("mm:ss").format(mediaPlayer?.currentPosition)
                            runOnUiThread {
                                binding.seekBar.progress = currentPosition
                                binding.playduration.text = strCurrentPosition
                            }
                            try {
                                delay(1000)
                                binding.seekBar.incrementProgressBy(1000)
                            } catch (e: java.lang.Exception) {
                                Log.e("PlayActivity", "delay 오류발생 ${e.printStackTrace()}")
                            }
                        }
                        if (pauseFlag == false) {
                            runOnUiThread {
                                binding.seekBar.progress = 0
                                binding.playbutton.setImageResource(R.drawable.play_24)
                                binding.playduration.text = "00:00"
                            }
                        }
                    }
                }
            }
            R.id.nextmusicbutton ->{
                if (currentposition < playList!!.size - 1) {
                    ++currentposition
                } else {
                    currentposition = 0
                }
                setReplay()
                start()
            }
            R.id.backmusicbutton -> {
                if (currentposition == 0) {
                    currentposition =playList!!.size - 1
                } else {
                    --currentposition
                }
                setReplay()
                start()

            }
        }
    }
    fun setReplay(){
        mediaPlayer?.stop()
        mp3playerJob?.cancel()
        musicData = playList?.get(currentposition) as MusicData

        // 음악 파일객체 가져옴
        mediaPlayer = MediaPlayer.create(this, musicData?.getMusicUri())
        binding.seekBar.progress = 0
        binding.playduration.text = "00:00"

        // view Binding 진행 (activity_play와  MusicData 값을 이어줌)
        binding.albumtitle.text = musicData?.title
        binding.albumartist.text = musicData?.artist
        binding.totalduration.text = SimpleDateFormat("mm:ss").format(musicData?.duration)
        binding.playduration.text = "00:00"
        val bitmap = musicData?.getAlbumBitmap(this, ALBUM_IMAGE_SIZE)
        if (bitmap != null) { binding.albumImage.setImageBitmap(bitmap) }
        else { binding.albumImage.setImageResource(R.drawable.music_24) }
    }
    fun start(){
        //music play
        mediaPlayer?.start()
        binding.playbutton.setImageResource(R.drawable.pause_24)
        pauseFlag = false

        // 코루틴으로 음악을 재생
        val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
        mp3playerJob = backgroundScope.launch {
            while (mediaPlayer!!.isPlaying) {
                var currentPosition = mediaPlayer?.currentPosition!!
                // 코루틴속에서 화면의 값을 변동시키고자 할 때 runOnUiThread 사용
                var strCurrentPosition = SimpleDateFormat("mm:ss").format(mediaPlayer?.currentPosition)
                runOnUiThread {
                    binding.seekBar.progress = currentPosition
                    binding.playduration.text = strCurrentPosition
                }
                try {
                    delay(1000)
                    binding.seekBar.incrementProgressBy(1000)
                } catch (e: java.lang.Exception) {
                    Log.e("PlayActivity", "delay 오류발생 ${e.printStackTrace()}")
                }
            }
            if (pauseFlag == false) {
                runOnUiThread {
                    binding.seekBar.progress = 0
                    binding.playbutton.setImageResource(R.drawable.play_24)
                    binding.playduration.text = "00:00"
                }
            }
        }
    }

    override fun onBackPressed() {
        mp3playerJob?.cancel()
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        finish()
    }
}