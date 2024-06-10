package za.ac.iie.practicumweatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class DetailedView : AppCompatActivity() {

    // Declarations of palettes with lateinit modifier for deferred initialisation
    private lateinit var btnBack: Button                                                            //(Mhlanga, 2024)
    private lateinit var txtDay: TextView                                                           //(Mhlanga, 2024)
    private lateinit var txtMin: TextView                                                           //(Mhlanga, 2024)
    private lateinit var txtMax: TextView                                                           //(Mhlanga, 2024)
    private lateinit var txtCon: TextView                                                           //(Mhlanga, 2024)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view)

        // Initialize views
        btnBack = findViewById(R.id.btnBack)                                                        //(Mhlanga, 2024)
        txtDay = findViewById(R.id.txtDay)                                                          //(Mhlanga, 2024)
        txtMin = findViewById(R.id.txtMin)                                                          //(Mhlanga, 2024)
        txtMax = findViewById(R.id.txtMax)                                                          //(Mhlanga, 2024)
        txtCon = findViewById(R.id.txtCon)                                                          //(Mhlanga, 2024)

        // Get the arrays from the Intent
        val minTemp = intent.getIntArrayExtra("minTemp") ?: intArrayOf()                      //(Developers, 2024)
        val maxTemp = intent.getIntArrayExtra("maxTemp") ?: intArrayOf()                      //(Developers, 2024)
        val weatherCon = intent.getStringArrayExtra("weatherCon") ?: arrayOf()                //(Developers, 2024)
        val weekDays = intent.getStringArrayExtra("weekDays") ?: arrayOf()                    //(Developers, 2024)

        // Display the arrays
        val daysText = weekDays.joinToString(separator = "\n")                                      //(Mhlanga, 2024)
        val minTempText = minTemp.joinToString(separator = "\n")                                    //(Mhlanga, 2024)
        val maxTempText = maxTemp.joinToString(separator = "\n")                                    //(Mhlanga, 2024)
        val weatherConText = weatherCon.joinToString(separator = "\n")                              //(Mhlanga, 2024)

        txtDay.text = "Days:\n$daysText"                                                            //(IIE, 2024)
        txtMin.text = "Min Temperatures:\n$minTempText"                                             //(IIE, 2024)
        txtMax.text = "Max Temperatures:\n$maxTempText"                                             //(IIE, 2024)
        txtCon.text = "Conditions:\n$weatherConText"                                                //(IIE, 2024)

        btnBack.setOnClickListener {
            //Pop-up msg for when transition to detailed view clicked
            Toast.makeText(this@DetailedView, "Transitioning", Toast.LENGTH_SHORT)
                .show()                                                                             //(IIE, 2024)

            //Explicit intent to specify the Detailed View class should be started
            val intent3 = Intent(this, MainScreen::class.java)                         //(IIE, 2024)

            // Start Activity MainScreen using the intent created
            startActivity(intent3)                                                                  //(IIE, 2024)

            // Logs to provide warnings, debugging assistance and verbose comments (manual testing)
            // Debug logs allow me to test whether the screen transition executes
            Log.w("Back btn", "Starting of the second screen not " +                       //(IIE, 2024)
                    "tested for exceptions.")
            Log.d("Back btn", "Button 'CLICK TO START!' clicked to navigate" +             //(IIE, 2024)
                    " to MainActivityMainScreen.")
        }
    }
}