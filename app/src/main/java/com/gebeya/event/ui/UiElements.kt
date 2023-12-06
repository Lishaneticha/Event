package com.gebeya.event.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GebeyaTextField(
    mod: Modifier = Modifier
){
    TextField(
        modifier = mod,
        value = "",
        onValueChange = {}
    )
}

@Composable
fun GebeyaText(){
    Text(
        text = "default value"
    )
}

@Composable
fun GebeyaButton(
    textColor: Color
){
    Button(
        onClick = {  },
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor
        )
    ) {
        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = ""
        )
        Text(text = "click me")
    }
}