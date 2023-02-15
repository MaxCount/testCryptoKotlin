package by.khomichenko.testcrypto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import by.khomichenko.testcrypto.domain.models.pair.PairModel
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.wavesplatform.com"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        getData()

    }

    private fun getData() {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WavesApi::class.java)

        val retrofitData = retrofitBuilder.getExchangeRange()

        retrofitData.enqueue(object : Callback<PairModel?> {
            override fun onResponse(call: Call<PairModel?>, response: Response<PairModel?>) {

                Log.d("MainActivity", "Status Code = " + response.code());
                val responseBody = response.body()!!
                val textView : TextView? = findViewById(R.id.txt)

                    textView?.text = responseBody.data[0].data.lastPrice.toString()

            }

            override fun onFailure(call: Call<PairModel?>, t: Throwable) {
            }
        })
    }
}