package com.example.weathertest

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter(var items: Array<ModelWeather>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAdapter.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_weather, parent, false)
        return RecyclerView.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WeatherAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    override fun getItemCount() = items.count()
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    @SuppressLint("SetTextI18n")
    fun setItem(item: ModelWeather) {
        val imgWeather = itemView.findViewById<ImageView>(R.id.imgWeather)  // 날씨 이미지
        val tvTime = itemView.findViewById<TextView>(R.id.tvTime)           // 시각
        val tvHumidity = itemView.findViewById<TextView>(R.id.tvHumidity)   // 습도
        val tvTemp = itemView.findViewById<TextView>(R.id.tvTemp)           // 온도


        imgWeather.setImageResource(getRainImage(item.rainType, item.sky))
        tvTime.text = getTime(item.fcstTime)
        tvHumidity.text = item.humidity + "%"
        tvTemp.text = item.temp + "°"
    }
}

fun getTime(factTime: String): String {
    if (factTime != "지금") {
        var hourSystem: Int = factTime.toInt()
        var hourSystemString = ""


        if (hourSystem == 0) {
            return "오전 12시"
        } else if (hourSystem > 2100) {
            hourSystem -= 1200
            hourSystemString = hourSystem.toString()
            return "오후 ${hourSystemString[0]}${hourSystemString[1]}시"


        } else if (hourSystem == 1200) {
            return "오후 12시"
        } else if (hourSystem > 1200) {
            hourSystem -= 1200
            hourSystemString = hourSystem.toString()
            return "오후 ${hourSystemString[0]}시"

        } else if (hourSystem >= 1000) {
            hourSystemString = hourSystem.toString()

            return "오전 ${hourSystemString[0]}${hourSystemString[1]}시"
        } else {

            hourSystemString = hourSystem.toString()

            return "오전 ${hourSystemString[0]}시"

        }

    } else {
        return factTime
    }
}

fun getRainImage(rainType: String, sky: String): Int {
    return when (rainType) {
        "0" -> getWeatherImage(sky)
        "1" -> R.drawable.rain_24
        "2" -> R.drawable.hail_24
        "3" -> R.drawable.snow_24
        "4" -> R.drawable.brash_24
        else -> getWeatherImage(sky)
    }
}
fun getWeatherImage(sky : String) : Int {
    // 하늘 상태
    return when(sky) {
        "1" -> R.drawable.sun_24                       // 맑음
        "3" ->  R.drawable.cloudy_24                     // 구름 많음
        "4" -> R.drawable.blur_24                 // 흐림
        else -> R.drawable.ic_launcher_foreground   // 오류
    }
}

