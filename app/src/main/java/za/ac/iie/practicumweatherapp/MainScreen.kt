package za.ac.iie.practicumweatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.math.roundToInt

class MainScreen : AppCompatActivity() {

    // Initialise the array off type string for the fixed days of the week
    private val weekDays = arrayOf<String>("Monday", "Tuesday", "Wednesday", "Thursday",            //(IIE, 2024)
        "Friday", "Saturday", "Sunday")

    // Initialise the empty array of type integer for the minimum temperatures in the form of a
    private var minTemp = arrayOf<Int>()                                                            //(IIE, 2024)

    // Initialise the empty array of type integer for the maximum temperatures
    private var maxTemp = arrayOf<Int>()                                                            //(IIE, 2024)

    // Initialise the empty array of type string for the weather conditions
    private var weatherCon = arrayOf<String>()                                                      //(IIE, 2024)

    // Initialise the variables used to calculate total temp and ave temp
    private var minSum = 0                                                                          //(IIE, 2024)
    private var maxSum =0                                                                           //(IIE, 2024)
    private var total = 0                                                                           //(IIE, 2024)
    private var ave = 0.0                                                                           //(IIE, 2024)

    // Declarations of palettes with lateinit modifier for deferred initialisation
    private lateinit var enterMin: EditText                                                         //(Mhlanga, 2024)
    private lateinit var enterMax: EditText                                                         //(Mhlanga, 2024)
    private lateinit var enterCondition:EditText                                                    //(Mhlanga, 2024)
    private lateinit var txtDisplayAve: TextView                                                    //(Mhlanga, 2024)
    private lateinit var btnAve: Button                                                             //(Mhlanga, 2024)
    private lateinit var btnDetail: Button                                                          //(Mhlanga, 2024)
    private lateinit var btnClear: Button                                                           //(Mhlanga, 2024)
    private lateinit var btnExit2: Button                                                           //(Mhlanga, 2024)
    private lateinit var btnAdd: Button                                                             //(Mhlanga, 2024)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        // Initialisation of palettes
        enterMin = findViewById(R.id.enterMin)                                                      //(IIE, 2024)
        enterMax = findViewById(R.id.enterMax)                                                      //(IIE, 2024)
        enterCondition = findViewById(R.id.enterCondition)                                          //(IIE, 2024)
        txtDisplayAve = findViewById(R.id.txtDisplayAve)                                            //(IIE, 2024)
        btnAve = findViewById(R.id.btnAve)                                                          //(IIE, 2024)
        btnDetail = findViewById(R.id.btnDetail)                                                    //(IIE, 2024)
        btnClear = findViewById(R.id.btnClear)                                                      //(IIE, 2024)
        btnExit2 = findViewById(R.id.btnExit2)                                                      //(IIE, 2024)
        btnAdd = findViewById(R.id.btnAdd)                                                          //(IIE, 2024)


        // Lambda expression for the add button function
        btnAdd.setOnClickListener {                                                          //(IIE, 2024)

            // Call the function to add to the arrays
            addToArrays()
            // Pop-up message to indicate user input added
            Toast.makeText(this@MainScreen, "Added", Toast.LENGTH_SHORT)
                .show()                                                                             //(IIE, 2024)
            Log.d("Add btn", "addToArray function called")                                 //(IIE, 2024)
        }

        // Lambda expression for the compute average button function
        btnAve.setOnClickListener {                                                          //(IIE, 2024)

            // Call the function to calculate the average temperature
            computeAve()
            // Pop-up message to indicate the compute average function is called
            Toast.makeText(this@MainScreen, "Added", Toast.LENGTH_SHORT)
                .show()                                                                             //(IIE, 2024)
            Log.d("Compute ave btn", "calculate average function called")                  //(IIE, 2024)

        }

        // Lambda expression for the transition button function
        btnDetail.setOnClickListener {                                                       //(IIE, 2024)

            detailView()
            Toast.makeText(this@MainScreen, "Transitioning", Toast.LENGTH_SHORT)
                .show()                                                                             //(IIE, 2024)
            Log.d("Detailed View btn", "transition function called")                       //(IIE, 2024)
        }

        // Lambda expression for the clear button function
        btnClear.setOnClickListener {                                                        //(IIE, 2024)
            clearAll()
            Toast.makeText(this@MainScreen, "Cleared", Toast.LENGTH_SHORT)
                .show()                                                                             //(IIE, 2024)
            Log.d("Clear btn", "clear function called")                                    //(IIE, 2024)
        }

        // Lambda expression for the second exit button function
        btnExit2.setOnClickListener {                                                        //(IIE, 2024)
            exit()
            Toast.makeText(this@MainScreen, "Exiting", Toast.LENGTH_SHORT)
                .show()                                                                             //(IIE, 2024)
            Log.d("Exit btn", "exit function called")                                      //(IIE, 2024)
        }


    }
    private fun addToArrays(){                                                                      //(Mhlanga, 2024)

        // Initialise variables for user input
        val min = enterMin.text.toString().toIntOrNull()                                            //(Mhlanga, 2024)
        val max = enterMax.text.toString().toIntOrNull()                                            //(Mhlanga, 2024)
        val condition = enterCondition.toString()                                                   //(IIE, 2024)

        // if statement to check for valid inputs
        if (min != null && max != null && condition.isNotBlank()){                                  //(Mhlanga, 2024)

            // Add the user input to the relative arrays
            minTemp = minTemp + min                                                                 //(IIE, 2024)
            maxTemp = maxTemp + max                                                                 //(IIE, 2024)
            weatherCon = weatherCon + condition                                                     //(IIE, 2024)

            // Clear the edit text views for next input
            enterMin.text.clear()                                                                   //(Mhlanga, 2024)
            enterMax.text.clear()                                                                   //(Mhlanga, 2024)
            enterCondition.text.clear()                                                             //(Mhlanga, 2024)

            // Reset the error message
            txtDisplayAve.text = "Average temperature for the week = "                              //(IIE, 2024)

        } else {
            // Error message and log to indicate user input(s) empty
            txtDisplayAve.text = "ERROR: Please enter valid data"                                   //(IIE, 2024)
            Log.d("addToArrays", "error - invalid input")                                  //(IIE, 2024)
        }

        Log.d("addToArrays", "execute function - input data")                              //(IIE, 2024)
    }
    private fun computeAve(){                                                                       //(Mhlanga, 2024)

        // if statement to check if the arrays are assigned values
        if (minTemp.isNotEmpty() && maxTemp.isNotEmpty()) {

            // Loops to add the elements for each array
            for (min in minTemp) {                                                                  //(Mhlanga, 2024)
                minSum += min                                                                       //(IIE, 2024)
            }
            for (max in maxTemp){                                                                   //(Mhlanga, 2024)
                maxSum += max                                                                       //(IIE, 2024)
            }
            // Calculate the total and the average
            total = minSum + maxSum                                                                 //(IIE, 2024)
            ave = ((total /(minTemp.size + maxTemp.size)).toDouble())                               //(Mhlanga, 2024)

            // Display the average temperature
            txtDisplayAve.text = "Average temperature for the week = $ave"                          //(IIE, 2024)

        } else {
            // error message to indicate empty arrays
            txtDisplayAve.text = "ERROR: Please enter valid data into the arrays"                    //(IIE, 2024)
            Log.d("Compute Ave", "error - empty arrays")                                   //(IIE, 2024)
        }

        Log.d("computeAve", "execute function - calculate ave")                            //(IIE, 2024)
    }
    private fun detailView(){                                                                       //(Mhlanga, 2024)

        //Pop-up msg for when transition to detailed view clicked
        Toast.makeText(this@MainScreen, "Transitioning", Toast.LENGTH_SHORT)
            .show()                                                                                 //(IIE, 2024)

        //Explicit intent to specify the Detailed View class should be started
        val intent2 = Intent(this, DetailedView::class.java)                            //(IIE, 2024)

        // Pass the arrays to the DetailedView activity
        intent2.putExtra("minTemp", minTemp)                                                  //(Developers, 2024)
        intent2.putExtra("maxTemp", maxTemp)                                                  //(Developers, 2024)
        intent2.putExtra("weatherCon", weatherCon)                                            //(Developers, 2024)
        intent2.putExtra("weekDays", weekDays)                                                //(Developers, 2024)

        // Start Activity DetailedView using the intent created
        startActivity(intent2)                                                                      //(IIE, 2024)
        Log.d("detailView", "execute function - new screen")                               //(IIE, 2024)
    }
    private fun clearAll(){                                                                         //(Mhlanga, 2024)

        // Clear the edit text views for next input
        enterMin.text.clear()                                                                       //(Mhlanga, 2024)
        enterMax.text.clear()                                                                       //(Mhlanga, 2024)
        enterCondition.text.clear()                                                                 //(Mhlanga, 2024)

        // Set the arrays back to empty
        minTemp = arrayOf<Int>()                                                                    //(Mhlanga, 2024)
        maxTemp = arrayOf<Int>()                                                                    //(Mhlanga, 2024)
        weatherCon = arrayOf<String>()                                                              //(Mhlanga, 2024)


        Log.d("clearAll", "execute function - clear fields")                               //(IIE, 2024)

    }
    private fun exit(){                                                                             //(Mhlanga, 2024)

        // Pop-up msg to indicate the app will close
        Toast.makeText(this@MainScreen, "Exiting", Toast.LENGTH_SHORT)
            .show()                                                                                 //(IIE, 2024)
        // Closes all activities and exits the app
        finishAffinity()                                                                            //(starball, 2022)
        Log.d("exit", "execute function - close app")                                      //(IIE, 2024)
    }
}