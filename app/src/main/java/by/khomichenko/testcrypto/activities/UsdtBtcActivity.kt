package by.khomichenko.testcrypto.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.khomichenko.testcrypto.R

class UsdtBtcActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exchange_usdt)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val rate = intent.getStringExtra("rate")
        val textView: TextView? = findViewById(R.id.txt2)
        textView?.text = rate

        val button: Button = findViewById(R.id.button2)
        button.setOnClickListener{

            val editValue: EditText? = findViewById(R.id.editTextNumber3)
            val numderCoins = editValue?.text.toString()

            val textView7: TextView? = findViewById(R.id.textView5)
            textView7?.text = getPrice(rate?.toDoubleOrNull(), numderCoins.toDoubleOrNull()).toString()
        }

        val requestButton: Button = findViewById(R.id.button3)
        requestButton.setOnClickListener{
            Toast.makeText(this, "Data sended...", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getPrice(rate: Double?, editValue: Double?): Double {
        return editValue!! / rate!!
    }
}