package za.ac.iie.practicumweatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // Declarations of palettes with lateinit modifier for deferred initialisation
    private lateinit var btnExit: Button                                                            //(Mhlanga, 2024)
    private lateinit var btnStart: Button                                                           //(Mhlanga, 2024)
    // NOTE: the other palettes are unused and don't require declaration/initialisation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Deferred initialisation of functional palettes
        btnExit = findViewById(R.id.btnExit)                                                        //(Mhlanga, 2024)
        btnStart = findViewById(R.id.btnStart)                                                      //(Mhlanga, 2024)

        // Lambda expression for the button function
        btnStart.setOnClickListener {                                                        //(IIE, 2024)

            //Pop-up msg for when start button clicked
            Toast.makeText(this@MainActivity, "Starting", Toast.LENGTH_SHORT)
                .show()                                                                             //(IIE, 2024)

            //Explicit intent to specify the Activity MainScreen class should be started
            val intent = Intent(this, MainScreen::class.java)                          //(IIE, 2024)

            // Start Activity MainScreen using the intent created
            startActivity(intent)                                                                   //(IIE, 2024)

            // Logs to provide warnings, debugging assistance and verbose comments (manual testing)
            // Debug logs allow me to test whether the screen transition executes
            Log.w("Start btn", "Starting of the second screen not " +                      //(IIE, 2024)
                    "tested for exceptions.")
            Log.d("Start btn", "Button 'CLICK TO START!' clicked to navigate" +            //(IIE, 2024)
                    " to MainActivityMainScreen.")
            Log.v("Functionality", "A new screen was added per the specifications layed-" +
                    "out for the practicum.")                                                       //(IIE, 2024)
        }

        // Lambda expression for the button function
        btnExit.setOnClickListener {                                                         //(IIE, 2024)

            // Pop-up msg to indicate the app will close
            Toast.makeText(this@MainActivity, "Exiting", Toast.LENGTH_SHORT)
                .show()                                                                             //(IIE, 2024)
            // Closes all activities and exits the app
            finishAffinity()                                                                        //(starball, 2022)

        }
        // Debug log to ensure the Main Activity is initialised upon start-up
        Log.d("MainActivity", "onCreate method called when MainActivity initialised.")     //(IIE, 2024)
    }
}