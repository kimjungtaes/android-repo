package com.example.googlemappro

import com.example.googlemappro.data.Library
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

//http://openapi.seoul.go.kr:8088/6c4e436574786f7836304575424570/json/SeoulPublicLibraryInfo/1/5/

class SeoulOpenApi {
    companion object{
        val DOMAIN = "http://openapi.seoul.go.kr:8088/"
        val API_KEY = "6c4e436574786f7836304575424570"
        val MAX_COUNT = 5
    }
}
