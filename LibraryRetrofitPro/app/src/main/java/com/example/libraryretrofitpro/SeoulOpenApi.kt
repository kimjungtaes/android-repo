package com.example.libraryretrofitpro

import com.example.libraryretrofitpro.data.Library
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

//************************
//공공 데이터를 가져온 API주소및 암호키등등
//http://openapi.seoul.go.kr:8088/API/json/SeoulPublicLibraryInfo/1/5/
//api class
class SeoulOpenApi {
    companion object{
        val DOMAIN = "http://openapi.seoul.go.kr:8088/"
        val API_KEY = "API"
        val LIST_TOTAL_COUNT = 5
    }
}
//***************************
//interface class (추상메소드) return값은 library
interface SeoulOpenService {
    @GET("{api_key}/json/SeoulPublicLibraryInfo/1/{end}")
    fun getLibraryres(@Path("api_key")key:String, @Path("end")limit:Int): Call<Library>
}
