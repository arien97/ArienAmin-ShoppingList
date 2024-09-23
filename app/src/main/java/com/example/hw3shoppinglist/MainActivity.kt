package com.example.hw3shoppinglist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingList()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Composable
fun ShoppingList() {
    val items = remember { mutableStateListOf<Triple<String, Boolean, String>>() }
    var newItemText by remember { mutableStateOf("") }
    var newItemQuantity by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.TopCenter) {
        Text(text = "Shopping List",
            modifier = Modifier.padding(16.dp),
            color = Color(0xFFbd300d),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
                .border(1.dp, Color(0xFFbd300d)),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Text(text = "Item Name")
            Text(text = "Quantity")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp, top = 80.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            items(items) { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Checkbox(
                        checked = item.second,
                        onCheckedChange = { isChecked ->
                            val index = items.indexOf(item)
                            items[index] = item.copy(second = isChecked)
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFFbd300d),
                            uncheckedColor = Color.Black
                        )
                    )
//                    Text(text = item.first, modifier = Modifier.padding(start = 8.dp))
                    // I made these a textfield object instead of normal text so that you can edit anything if you made a mistake
                    TextField(
                        value = item.first,
                        onValueChange = { newText ->
                            val index = items.indexOf(item)
                            items[index] = item.copy(first = newText)
                        },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .width(200.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            containerColor = Color.Transparent)
                    )
                    TextField(
                        value = item.third,
                        onValueChange = { newQuantity ->
                            val index = items.indexOf(item)
                            items[index] = item.copy(third = newQuantity)
                        },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .width(80.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = Color(0xFFbd300d),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            containerColor = Color(0xFFfcccc0)
                        )
                    )
                }
            }
        }

        // this is to add items to the list
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            TextField(
                value = newItemText,
                onValueChange = { newItemText = it },
                label = { Text("New Item", color = Color(0xFFbd300d)) },
                modifier = Modifier.weight(1f),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color(0xFFbd300d),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color(0xFFfcccc0)
                )
            )
            TextField(
                value = newItemQuantity,
                onValueChange = { newItemQuantity = it },
                label = { Text("Quantity", color = Color(0xFFbd300d)) },
                modifier = Modifier.width(100.dp),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color(0xFFbd300d),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color(0xFFfcccc0)
                )
            )
            Button(
                onClick = {
                    if (newItemText.isNotBlank() && newItemQuantity.isNotBlank()) {
                        items.add(Triple(newItemText, false, newItemQuantity))
                        newItemText = ""
                        newItemQuantity = ""
                    }
                },
                modifier = Modifier.padding(start = 8.dp),
                colors = ButtonDefaults.buttonColors( Color(0xFFbd300d)),
            ) {
                Text("Add Item")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun Preview() {

}