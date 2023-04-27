package com.example.weathertest

import com.google.android.gms.awareness.state.Weather
import retrofit2.Response
import retrofit2.http.Query

//http://apis.data.go.kr/1360000/
// VilageFcstInfoService_aNHLR9IBnouaDhmFOVX3QQulJuGzb%2Br7uBfR4WEExq%2FcCiHXZaFuWn2tRjtNMonEMP5CqoaHB2z%2FibUtt19KJw%3D%3D/getUltraSrtNcst

class WeatherApi {

    companion object {
        val DOMAIN = "http://apis.data.go.kr/1360000"
        val API_KEY = "aNHLR9IBnouaDhmFOVX3QQulJuGzb%2Br7uBfR4WEExq%2FcCiHXZaFuWn2tRjtNMonEMP5CqoaHB2z%2FibUtt19KJw%3D%3D"
        val MAX_COUNT = 5
        suspend fun getWeather(
            @Query("dataType") dataType: String,
            @Query("numOfRows") numOfRows: Int,
            @Query("pageNo") pageNo: Int,
            @Query("base_date") baseDate: Int,
            @Query("base_time") baseTime: Int,
            @Query("nx") nx: String,
            @Query("ny") ny: String
        ): Response<Weather>{
            return
        }


    }
}