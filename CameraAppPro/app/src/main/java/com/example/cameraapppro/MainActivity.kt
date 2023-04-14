package com.example.cameraapppro


import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.cameraapppro.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var filePath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //1. 인텐트를 통해서 갤러리 앱에서 클릭을 하면 클릭된 이미지 Uri 가져와서 , 컨텐트리졸버를 이용해서 inputstream , BitMapFactory 를 통해서
        // 이미지 뷰 가져온다.
        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){

            if(it.resultCode == RESULT_OK){
                //1.1 이미지를 가져오면 oom 발생할 수 있으므로 , 화면에 출력될 원하는 사이즈 비율설정
                val inSampleSize = calculateInSampleSize(Uri.fromFile(File(filePath)),
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize))
                //1.2 비트맵 옵션설정 비율설정
                val option = BitmapFactory.Options()
                option.inSampleSize = inSampleSize

                try {
                    //1.4 inputStream 으로 BitmapFactory를 이용해서 이미지를 가져온다. (oom 방지하기 위해서, option 사이즈비율 저장함)
                    var bitmap = BitmapFactory.decodeFile(filePath, option)
                    //1.5 이미지뷰에 비트맵을 저장하시키면 된다.
                    bitmap?.let{
                        binding.ivPicture.setImageBitmap(bitmap)
                    }?:let{
                        Log.e("MainActivity", "bitmapFactory를 통해서 가져온 비트맵 null 발생")
                    }
                }catch (e: Exception){
                    Log.e("MainActivity", "${e.printStackTrace()}")
                }
            }
        }//end of registerForActivityResult

        //2. 갤러리앱에 암시적 인텐트방법으로 요청
        binding.btnCallImage.setOnClickListener {
            //2.1 카메라앱이 저장될 파일명을 만듬
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            //2.2 카메라앱이 저장될 앱폴더 위치 가져옴
            val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            //2.3 실제파일명만들어서 앱폴더 위치 저장한다.
            val file = File.createTempFile("__jpg_${timeStamp}_",".jpg", storageDir)
            filePath = file.absolutePath
            //2.4 filePath Provider uri 경로 만들어줌
            val uri = FileProvider.getUriForFile(this, "com.example.cameraapppro.fileprovider", file)

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            requestLauncher.launch(intent)
        }
    }

    //이미지 비율을 계산하는 함수
    fun calculateInSampleSize(uri: Uri, reqWidth: Int, reqHight: Int) : Int{
        val option = BitmapFactory.Options()
        //inJustDecodeBounds = true 이미지가져오지말고 이미지정보만 줄것을 요청
        option.inJustDecodeBounds = true

        try{
            //contentResolver를 이이미지 정보를 다시가져온다.
            var inputStream =  contentResolver.openInputStream(uri)
            //진짜 inputStream 통해서 비트맵을 가져오는것이 아니라 , 비트맵 정보만 option 저장해서 가져온다.
            BitmapFactory.decodeStream(inputStream, null, option)
            inputStream?.close()
            inputStream =null
        }catch (e: java.lang.Exception){
            Log.e("MainActivity", "calculateInSampleSize inputstrem ${e.printStackTrace()}")
        }
        //갤러이앱에 가져올 실제 이미지 사이즈
        val height = option.outHeight
        val width = option.outWidth
        var inSampleSize = 1;

        if(height > reqHight || width > reqWidth){
            val halfHight = height /2
            val halfWidth = width /2
            while(halfHight / inSampleSize >= reqHight && halfWidth/inSampleSize >= reqWidth){
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}