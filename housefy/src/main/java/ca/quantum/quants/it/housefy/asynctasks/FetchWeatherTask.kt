package ca.quantum.quants.it.housefy.asynctasks

/*
 * @author Artem Tsurkan, n01414146
 * @author Wenyuan Yu, n01403697
 * @author Kyrylo Lvov, n01414058
 * @course Software Project - CENG-322-0NA
 */

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.util.Log
import androidx.compose.runtime.MutableState
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.math.roundToInt

class FetchWeatherTask(
    private val context: Context,
    private val temperature: MutableState<String>,
    private val feelsLike: MutableState<String>,
    private val icon: MutableState<String>
) : AsyncTask<String, Void, String>() {

    override fun doInBackground(vararg params: String?): String {
        var response = ""

        try {
            val url =
                URL("https://api.openweathermap.org/data/2.5/weather?lat=${params[0]}&lon=${params[1]}&appid=${params[2]}")
            val httpURLConnection = url.openConnection() as HttpURLConnection

            if (httpURLConnection.responseCode == 200) {
                val inputStream = httpURLConnection.inputStream
                val inputStreamReader = InputStreamReader(inputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                val sb = StringBuilder()
                var line: String?
                while (bufferedReader.readLine().also { line = it } != null) {
                    sb.append(line)
                }
                response = sb.toString()
            } else {
                Log.i("FetchWeatherTask", "HTTPURLCONNECTION Response not 200")
            }
        } catch (e: Exception) {
            Log.e("FetchWeatherTask", e.toString())
        }

        return response
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        if (!result.isNullOrEmpty()) {
            try {
                val jsonObject = JSONObject(result)
                val mainObject = jsonObject.getJSONObject("main")

                val weatherArray = jsonObject.getJSONArray("weather")
                val firstWeatherObject = weatherArray.getJSONObject(0)

                val temp = mainObject.getDouble("temp")
                val feelsLikeTemp = mainObject.getDouble("feels_like")
                val weatherIcon = firstWeatherObject.getString("icon")

                Log.i("FetchWeatherTask", "temp: $temp")

                // The API provides temperature in Kelvin. The following conversion transforms the temperature to Celsius.
                temperature.value = (temp - 273.15).roundToInt().toString()
                feelsLike.value = (feelsLikeTemp - 273.15).roundToInt().toString()
                icon.value = weatherIcon

                saveWeatherData(context, temperature.value, feelsLike.value, icon.value)
            } catch (e: Exception) {
                Log.e("FetchWeatherTask", "Error in parsing JSON")
            }
        }
    }
}

fun getWeatherData(
    context: Context,
    lat: String,
    lon: String,
    apiKey: String,
    temperature: MutableState<String>,
    feelsLike: MutableState<String>,
    icon: MutableState<String>
) {
    FetchWeatherTask(context, temperature, feelsLike, icon).execute(lat, lon, apiKey)
}

fun saveWeatherData(context: Context, temp: String, feelsLike: String, icon: String) {
    val sharedPreferences = context.getSharedPreferences("weather_data", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("temp", temp)
        putString("feelsLike", feelsLike)
        putString("icon", icon)
        apply()
    }
}

fun getSavedWeatherData(context: Context): Map<String, String?> {
    val sharedPreferences = context.getSharedPreferences("weather_data", Context.MODE_PRIVATE)
    return mapOf(
        "temp" to sharedPreferences.getString("temp", "N/A"),
        "feelsLike" to sharedPreferences.getString("feelsLike", "N/A"),
        "icon" to sharedPreferences.getString("icon", "N/A")
    )
}

@SuppressLint("ServiceCast")
fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}