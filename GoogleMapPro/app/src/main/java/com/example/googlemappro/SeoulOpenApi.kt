package com.example.googlemappro

import com.example.googlemappro.data.Library
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

//http://openapi.seoul.go.kr:8088/API/json/SeoulPublicLibraryInfo/1/5/

class SeoulOpenApi {
    companion object{
        val DOMAIN = "http://openapi.seoul.go.kr:8088/"
        val API_KEY = "API"
        val MAX_COUNT = 5
    }
}
