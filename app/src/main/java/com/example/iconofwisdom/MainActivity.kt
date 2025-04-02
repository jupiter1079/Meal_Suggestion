package com.example.iconofwisdom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var icon_fname by remember {
                mutableStateOf("")
            }

            var words_of_wisdom by remember {
                mutableStateOf("")
            }
           Column(
               horizontalAlignment = Alignment.CenterHorizontally,
               modifier = Modifier.fillMaxSize()
           ) {
               Text(
                   text = "Meal Suggestion",
                   fontSize = 30.sp,
                   fontWeight = FontWeight.Black
               )

               Divider()
               Spacer(modifier = Modifier.size(30.dp))

               OutlinedTextField(
                   value = icon_fname ,
                   onValueChange = {text ->
                       icon_fname = text
                   },
                   placeholder = {
                       Text(text = "Enter Time of the day.")
                   }
               )
               Spacer(modifier = Modifier.size(30.dp))

               Row {
                   Button(onClick = {
                       words_of_wisdom = when(icon_fname){
                           "Morning" -> "Oats served with milk and a glass of orange juice." +
                                   " Toast with bacon and avocado " +
                                   "or Eggs with cherry tomatoes,feta and crusty bread. "

                           "Mid-Morning" -> "Garden salad served with blue cheese salad dressing or" +
                                   " smoothie with yogurt, berries, and a handful of spinach."

                           "Afternoon" -> "Chicken Wrap – Whole-wheat tortilla with grilled chicken, hummus, spinach, and roasted red peppers or" +
                                   "Rice, potato salad served with chicken curry."

                           "Mid-Afternoon" -> " A small bun of cheese and chicken mayo or Greek Yogurt with Honey."

                           "Dinner" -> "Pap, Steak and gravy or Mash creamy spinach and hake."

                           else -> "Please write correct time of the day"
                       }
                   }) {
                       Text(text = "Search")
                   }
                   Button(onClick = {
                       icon_fname = ""
                       words_of_wisdom = ""
                   }) {
                       Text(text = "Reset")
                   }
               }
                Text(text = "Meal Suggestion $icon_fname is:")
                Text(text = words_of_wisdom)
            }
        }
    }
}
