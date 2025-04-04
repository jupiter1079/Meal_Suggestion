package com.example.iconofwisdom

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    // Tag for logging
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // States for user inputs and meal suggestion
            var iconFname by remember { mutableStateOf("") }
            var wordsOfWisdom by remember { mutableStateOf("") }
            var mealImage by remember { mutableStateOf(R.drawable.ic_launcher_foreground) } // Default image

            // Layout structure
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp) // Padding for better spacing
            ) {
                // App logo
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(120.dp) // Adjusted size for better UI
                )

                // Title Text
                Text(
                    text = "Meal Suggestion",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 16.dp) // Added vertical padding
                )

                // Divider for visual separation
                Divider(modifier = Modifier.padding(vertical = 16.dp))

                // Input field for time of the day
                OutlinedTextField(
                    value = iconFname,
                    onValueChange = { text ->
                        iconFname = text
                        Log.d(TAG, "User input: $text") // Logging user input for debugging
                    },
                    placeholder = { Text("Enter Time Of The Day (e.g., Morning, Afternoon)") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = iconFname.isNotEmpty() && wordsOfWisdom.isEmpty() // Highlight error state
                )

                Spacer(modifier = Modifier.size(20.dp))

                // Buttons for search and reset actions
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            // Generate meal suggestion based on time of day and change the image
                            val result = getMealSuggestion(iconFname)
                            wordsOfWisdom = result.first
                            mealImage = result.second // Set image based on the time of day
                            Log.d(TAG, "Meal suggestion for: $iconFname") // Logging the meal suggestion
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Meal Search")
                    }

                    Button(
                        onClick = {
                            // Reset the fields
                            iconFname = ""
                            wordsOfWisdom = ""
                            mealImage = R.drawable.ic_launcher_foreground // Reset to default image
                            Log.d(TAG, "Fields reset") // Logging reset action
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Reset")
                    }
                }

                // Divider for separation
                Divider(modifier = Modifier.padding(vertical = 16.dp))

                // Display the meal suggestion
                if (wordsOfWisdom.isNotEmpty()) {
                    Text(
                        text = "Meal Suggestion for $iconFname:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = wordsOfWisdom,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                // Image representation of the meal suggestion
                Image(
                    painter = painterResource(id = mealImage),
                    contentDescription = "Meal Suggestion",
                    modifier = Modifier.size(200.dp) // Adjusted size for better fit
                )
            }
        }
    }

    /**
     * Function to get meal suggestion and the corresponding image based on the time of the day.
     * @param timeOfDay The time of day entered by the user (e.g., Morning, Afternoon).
     * @return A Pair of meal suggestion and the corresponding image resource ID.
     */
    private fun getMealSuggestion(timeOfDay: String): Pair<String, Int> {
        return when (timeOfDay.lowercase()) {
            "morning" -> {
                // Breakfast image
                "Toast served with egg,bacon,sausage and beans." to R.drawable.breakfast_image
            }
            "mid-morning" -> {
                // Snack image
                "Chicken Mayo bagel" to R.drawable.snack_image
            }
            "afternoon" -> {
                // Lunch image
                "Beef burger served with home made fries and green salad." to R.drawable.lunch_image
            }
            "mid-afternoon" -> {
                // Snack image
                "Blue berry smoothie,Banana smoothie or strawberry smoothie." to R.drawable.afternoon_snack_image
            }
            "dinner" -> {
                // Dinner image
                "Lemon and herb Chicken served with veggies." to R.drawable.dinner_image
            }
            else -> {
                Log.e(TAG, "Invalid input: $timeOfDay") // Log error for invalid input
                "Please enter a valid time of day (Morning, Mid-Morning, Afternoon, Mid-Afternoon, Dinner)." to R.drawable.error_image
            }
        }
    }
}
