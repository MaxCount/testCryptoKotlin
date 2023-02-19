package by.khomichenko.testcrypto.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.khomichenko.testcrypto.R

class BtcUsdtActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exchange_btc)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val rate = intent.getStringExtra("rate")
        val textView: TextView? = findViewById(R.id.txt4)
        textView?.text = rate

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener{

            val editValue: EditText? = findViewById(R.id.editTextNumber)
            val numderCoins = editValue?.text.toString()

            val textView7: TextView? = findViewById(R.id.textView7)
            textView7?.text = getPrice(rate?.toDoubleOrNull(), numderCoins.toDoubleOrNull()).toString()
        }

        val requestButton: Button = findViewById(R.id.button5)
        requestButton.setOnClickListener{
            Toast.makeText(this, "Data sended...", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getPrice(rate: Double?, editValue: Double?): Double {
        return editValue!! * rate!!
    }
}