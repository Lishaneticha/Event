package com.gebeya.event.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun EventCard(
    name: String,
    date: LocalDate,
    image: Int
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = image),
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = name)
                Text(text = date.toString())
            }
        }
    }
}