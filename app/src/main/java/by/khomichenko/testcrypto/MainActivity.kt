package by.khomichenko.testcrypto

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.khomichenko.testcrypto.activities.BtcUsdtActivity
import by.khomichenko.testcrypto.activities.UsdtBtcActivity
import by.khomichenko.testcrypto.api.WavesApi
import by.khomichenko.testcrypto.databinding.MainPageBinding
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.ParseException

const val BASE_URL = "https://api.wavesplatform.com"
var rateToken: String? = null

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        try {
            getData()
        } catch (exception: ParseException){
            Toast.makeText(this, "Error while parsing the data", Toast.LENGTH_LONG).show()
        }

        binding = MainPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnBtc.setOnClickListener {
            val intentBtc = Intent(this, BtcUsdtActivity::class.java)
            intentBtc.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            intentBtc.putExtra("rate", rateToken)
            startActivity(intentBtc)
        }
        binding.btnUsdt.setOnClickListener {
            val intentUsdt = Intent(this, UsdtBtcActivity::class.java)
            intentUsdt.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            intentUsdt.putExtra("rate", rateToken)
            startActivity(intentUsdt)
        }
    }

    private fun getData() {

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofitBuilder.create(WavesApi::class.java)

        val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("COROUTINES","Coroutine exception handled $throwable")
        }

        CoroutineScope(Dispatchers.IO).launch(coroutineExceptionHandler) {
            val response = service.getExchangeRange()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val items = response.body()
                    if (items != null) {
                        val textView : TextView? = findViewById(R.id.rate)
                        textView?.text = items.data[0].data.lastPrice.toString()
                        rateToken = items.data[0].data.lastPrice.toString()
                    }
                }
            }
        }
    }
}