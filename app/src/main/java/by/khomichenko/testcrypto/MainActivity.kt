package by.khomichenko.testcrypto

import android.content.Intent
import android.net.TrafficStats
import android.os.Bundle
import android.os.StrictMode
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.khomichenko.testcrypto.activities.BtcUsdtActivity
import by.khomichenko.testcrypto.activities.UsdtBtcActivity
import by.khomichenko.testcrypto.databinding.MainPageBinding
import by.khomichenko.testcrypto.domain.models.pair.PairModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ServerSocket

const val BASE_URL = "https://api.wavesplatform.com"
var rateToken: String? = null

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainPageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)
        StrictMode.enableDefaults()
        getData()
        binding = MainPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnBtc.setOnClickListener {
            Toast.makeText(this, "Button was clicked", Toast.LENGTH_SHORT).show()

            val intentBtc = Intent(this, BtcUsdtActivity::class.java)
            intentBtc.putExtra("rate", rateToken)
            startActivity(intentBtc)
        }
        binding.btnUsdt.setOnClickListener {
            Toast.makeText(this, "Button was clicked", Toast.LENGTH_SHORT).show()

            val intentUsdt = Intent(this, UsdtBtcActivity::class.java)
            startActivity(intentUsdt)
        }
    }

    private fun getData() {

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WavesApi::class.java)

        val retrofitData = retrofitBuilder.getExchangeRange()

        retrofitData.enqueue(object :Callback<PairModel?> {
            override fun onResponse(call: Call<PairModel?>, response: Response<PairModel?>) {
                val responseBody = response.body()!!
                val textView : TextView? = findViewById(R.id.rate)

                textView?.text = responseBody.data[0].data.lastPrice.toString()
                rateToken = responseBody.data[0].data.lastPrice.toString()
            }

            override fun onFailure(call: Call<PairModel?>, t: Throwable) {
            }
        })
    }
}