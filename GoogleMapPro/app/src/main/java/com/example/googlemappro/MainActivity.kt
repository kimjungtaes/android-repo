package com.example.googlemappro

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.os.Build
import android.os.Build.VERSION_CODES.P
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.googlemappro.data.Library
import com.example.googlemappro.databinding.ActivityMainBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback {

    lateinit var binding: ActivityMainBinding
    var permmissionArray = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_NETWORK_STATE)
    var permissionflag = true
    //개인위치정보획득 객체참조변수
    lateinit var providerClient: FusedLocationProviderClient
    //개인정보 획득을 접속요청 참조변수
    lateinit var apiClient: GoogleApiClient
    //지도 객체
    var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //4. 퍼미션요청했을때 답변처리
        val requestMultiplePermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
            if(it.all{ permission -> permission.value == true}){
                apiClient.connect()
            }else{
                Toast.makeText(applicationContext, "모두 권한 허용을 해주어야만 구글맵을 사용가능",Toast.LENGTH_SHORT).show()
            }
        }

        //1.fragment에 지도를 동기화를 위해서 요청
        ((supportFragmentManager.findFragmentById(R.id.mapView)) as SupportMapFragment).getMapAsync(this)

        //2.위치정보를 획득요청
        providerClient = LocationServices.getFusedLocationProviderClient(this)

        //3. 위치정보를 획득하기 위한 접속
        apiClient = GoogleApiClient.Builder(this)
            .addApi(LocationServices.API)   // LocationServices.API 를 사용
            .addConnectionCallbacks(this)   //Location Provider 이용가능한 상태이면 콜백함수 연결
            .addOnConnectionFailedListener(this) //Location Provider 불가능할때 콜백함수 연결
            .build()

        //5. 퍼미션이 상태를 점검하고, 요청이 안되어 있으면 요청처리
        if(ContextCompat.checkSelfPermission(this,permmissionArray[0]) !== PackageManager.PERMISSION_GRANTED
            ||ContextCompat.checkSelfPermission(this,permmissionArray[1]) !== PackageManager.PERMISSION_GRANTED
            ||ContextCompat.checkSelfPermission(this,permmissionArray[2]) !== PackageManager.PERMISSION_GRANTED ){
            requestMultiplePermissionLauncher.launch(permmissionArray)
        }else{
            apiClient.connect()
        }
    }


    //1. Location Provider(내 위치정보를 준비하라 요청했을때) 준비가 가능한 상태가 되면 콜백함수
    override fun onConnected(p0: Bundle?) {
//        Log.e("MainActivity", "지도앱 위치 onConnected")
//        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
//            providerClient.lastLocation.addOnSuccessListener(this@MainActivity, object: OnSuccessListener<Location>{
//                override fun onSuccess(location: Location?) {
//                    location?.let{
//                        val latitude = location.latitude
//                        val longitude = location.longitude
//                        moveMap(latitude, longitude)
//                        Log.e("MainActivity", "지도앱 위치 ${latitude}  ${longitude}")
//                    }
//                }
//            })
//            apiClient.disconnect()
//        }//end of if

        //공공데이터를 가져오기
        loadLibraries()
    }





    //2. Location Provider 더 이상 사용 불가능상태가 되면 콜백함수
    override fun onConnectionSuspended(p0: Int) {
        Log.e("MainActivity", "Location Provider 불가능한 상태임 ${p0}")
    }
    //3. Location Provider의 위치 정보 제공자를 얻지 못했을때 발생
    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.e("MainActivity", "Location Provider의 위치 정보 제공실패 ${connectionResult.errorMessage}")
    }
    //4. 지도객체를 사용가능한 상태가 되었을때
    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap
    }

    private fun moveMap(latLng: LatLng, name: String? = null) {
        //위도 , 경도를 가지고 있는 클래스 선언
//        val latLng = LatLng(latitude, longitude)
        //카메라 위치선정
        val cameraPosition = CameraPosition.Builder()
            .target(latLng)
            .zoom(16f)
            .build()
        //구글맵에 카메라 위치정보 제공
        googleMap?.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        //마크업 아이콘설정 지도위에 배치 및 풍선도움말 지정
//        val markerOptions = MarkerOptions()
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
//        markerOptions.position(latLng)
//        markerOptions.title(name)
        //내가 지정한 마크업 아이콘 설정
        //롤리팝부터 마크업 아이콘 가져오는 방법이 변경
        var bitmapDrawable: BitmapDrawable
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            bitmapDrawable = getDrawable(R.drawable.img) as BitmapDrawable
        }else{
            bitmapDrawable = resources.getDrawable(R.drawable.img) as BitmapDrawable
        }
        val scaleBitmap = Bitmap.createScaledBitmap(bitmapDrawable.bitmap, 50,100,false)
        val descriptor = BitmapDescriptorFactory.fromBitmap(scaleBitmap)
        
        val markerOptions = MarkerOptions()
        markerOptions.icon(descriptor)
        markerOptions.position(latLng)
        name?.let { 
            markerOptions.title(it)
        }?.let { 
            markerOptions.title("Anonymous Library")
        }
        
        //구글맵 마크업등록
        googleMap?.addMarker(markerOptions)
    }

    private fun loadLibraries() {
       val retrofit = Retrofit.Builder()
           .baseUrl(SeoulOpenApi.DOMAIN)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
        val service = retrofit.create(SeoulOpenService::class.java)

        service.getLibrarys(SeoulOpenApi.API_KEY, SeoulOpenApi.MAX_COUNT)
            .enqueue(object : Callback<Library> {
                override fun onResponse(call: Call<Library>, response: Response<Library>) {
                    val result = response.body()
                    showLibraries(result)
                }

                override fun onFailure(call: Call<Library>, t: Throwable) {
                   Toast.makeText(this@MainActivity, "공공데이터 로드 실패",Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun showLibraries(result: Library?) {
        var latLng : LatLng

        //모든 위치를 포함한 좌표를 보여준다
       val latLngBounds = LatLngBounds.builder()


        result?.let {
            for (Library in it.SeoulPublicLibraryInfo.row){
                val name = Library.LBRRY_NAME
                val phone = Library.TEL_NO
                latLng = LatLng(Library.XCNTS.toDouble(), Library.YDNTS.toDouble())
                moveMap(latLng, name, phone)
                latLngBounds.include(latLng)
            }
        }

        //모든 좌표가 다 넣어지면 카메라 위치를 조절하면 된다
     val bounds = latLngBounds.build()
     val camera =  CameraUpdateFactory.newLatLngBounds(bounds, 50)
     googleMap?.moveCamera(camera)
    }

}