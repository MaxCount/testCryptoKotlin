package by.khomichenko.testcrypto.activities

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import by.khomichenko.testcrypto.R

class BtcUsdtActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exchange_usdt)

        val rate = intent.getStringExtra("rate")
        val textView: TextView? = findViewById(R.id.txt4)
        textView?.text = rate
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val editValue: EditText? = findViewById(R.id.editTextNumber)
        val numderCoins = editValue?.text.toString()
        val textView7: TextView? = findViewById(R.id.textView7)
        textView7?.text = getPrice(rate?.toDoubleOrNull(), numderCoins.toDoubleOrNull()).toString()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun getPrice(rate: Double?, editValue: Double?):Double {
        return editValue!! * rate!!
    }
}